package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.common.util.random.RandomCodeUtil
import com.sexysisters.tojserverv2.config.properties.JwtProperties
import com.sexysisters.tojserverv2.config.properties.MailProperties
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.PasswordMismatchException
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
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
    private val redisRepository: RedisRepository,
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(request: UserCommand.LoginRequest): UserInfo.Token {
        val email = request.email
        val user = userReader.findUserByEmail(email)
        checkPassword(request.password, user.password)

        val accessToken = jwtTokenProvider.createAccessToken(email)
        val refreshToken = jwtTokenProvider.createRefreshToken(email)
        redisRepository.setDataExpired(
            email,
            refreshToken,
            Duration.ofMillis(jwtProperties.refreshTokenValidTime),
        )

        return UserInfo.Token(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    private fun checkPassword(expected: String, actual: String) {
        val isOAuthAccount = expected == "OAUTH"
        val isWrongPassword = !passwordEncoder.matches(expected, actual)
        if (isOAuthAccount || isWrongPassword) {
            throw PasswordMismatchException()
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
    override fun sendCode(command: UserCommand.SendCodeRequest) {
        val email = command.email
        val code = RandomCodeUtil.generate(6)
        val time = MailProperties.AUTHENTCIATION_TIME
        redisRepository.setDataExpired(email, code, time)
        mailSender.sendMail(
            to = email,
            title = MailProperties.AUTHENTICATION_TITLE,
            content = code,
        )
    }

    @Transactional(readOnly = true)
    override fun authenticateCode(command: UserCommand.AuthenticateCode): Boolean {
        val email = command.email
        val expectedCode = command.code
        val actualCode = redisRepository.getData(email)

        val response = actualCode == expectedCode
        if (response) redisRepository.deleteData(email)
        return response
    }
}