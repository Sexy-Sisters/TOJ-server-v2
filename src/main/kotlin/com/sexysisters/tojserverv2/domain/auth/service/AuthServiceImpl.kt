package com.sexysisters.tojserverv2.domain.auth.service

import com.sexysisters.tojserverv2.common.util.random.RandomCodeUtil
import com.sexysisters.tojserverv2.config.properties.JwtProperties
import com.sexysisters.tojserverv2.config.properties.MailProperties
import com.sexysisters.tojserverv2.domain.auth.AuthCommand
import com.sexysisters.tojserverv2.domain.auth.AuthInfo
import com.sexysisters.tojserverv2.domain.auth.exception.AuthException
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtValidator
import com.sexysisters.tojserverv2.infrastructure.mail.MailSender
import com.sexysisters.tojserverv2.infrastructure.redis.RedisRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.util.Date

@Service
class AuthServiceImpl(
    private val mailSender: MailSender,
    private val userReader: UserReader,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties,
    private val jwtValidator: JwtValidator,
    private val redisRepository: RedisRepository,
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(command: AuthCommand.LoginRequest): AuthInfo.Token {
        val email = command.email
        val user = userReader.getUser(email)
        checkPassword(command.password, user.password)

        val accessToken = jwtTokenProvider.createAccessToken(email)
        val refreshToken = jwtTokenProvider.createRefreshToken(email)
        redisRepository.setDataExpired(
            email,
            refreshToken,
            Duration.ofMillis(jwtProperties.refreshTokenValidTime),
        )

        return AuthInfo.Token(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    private fun checkPassword(expected: String, actual: String) {
        val isOAuthAccount = expected == "OAUTH"
        val isWrongPassword = !passwordEncoder.matches(expected, actual)
        if (isOAuthAccount || isWrongPassword) {
            throw AuthException.PasswordMismatch()
        }
    }

    @Transactional(readOnly = true)
    override fun logout(accessToken: String) {
        val user = userReader.getCurrentUser()
        val parsedAccessToken = jwtTokenProvider.parseToken(accessToken)!!
        val remainTime = jwtTokenProvider.getExpiredTime(parsedAccessToken).time - Date().time
        redisRepository.setBlackList(parsedAccessToken, Duration.ofMillis(remainTime))
        redisRepository.deleteData(user.email)
    }

    @Transactional(readOnly = true)
    override fun getNewAccessToken(refreshToken: String): String {
        jwtValidator.validateRefreshToken(refreshToken)
        val email = jwtValidator.getEmail(refreshToken)
        return jwtTokenProvider.createAccessToken(email)
    }

    @Transactional(readOnly = true)
    override fun sendCode(command: AuthCommand.SendCodeRequest) {
        val email = command.email
        validateEmailDuplication(email)
        val code = RandomCodeUtil.generate(6)
        val time = MailProperties.AUTHENTCIATION_TIME
        redisRepository.setDataExpired(email, code, time)
        mailSender.sendMail(
            to = email,
            title = MailProperties.AUTHENTICATION_TITLE,
            content = code,
        )
    }

    private fun validateEmailDuplication(email: String) {
        if (userReader.existsUserByEmail(email)) {
            throw UserException.EmailAlreadyExists()
        }
    }

    @Transactional(readOnly = true)
    override fun authenticateCode(command: AuthCommand.AuthenticateCode): Boolean {
        val email = command.email
        val expectedCode = command.code
        val actualCode = redisRepository.getData(email)
        val response = actualCode == expectedCode
        if (response) redisRepository.deleteData(email)
        return response
    }
}