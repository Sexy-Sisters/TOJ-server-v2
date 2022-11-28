package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.config.properties.JwtProperties
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.PasswordMismatchException
import com.sexysisters.tojserverv2.domain.user.exception.UserErrorCode
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.mail.MailSenderImpl
import com.sexysisters.tojserverv2.infrastructure.redis.RedisRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.security.crypto.password.PasswordEncoder

class AuthServiceTest : BehaviorSpec({

    val userReader: UserReader = mockk()
    val encoder: PasswordEncoder = mockk()
    val jwtTokenProvider: JwtTokenProvider = mockk()
    val jwtProperties: JwtProperties = mockk(relaxed = true)
    val redisRepository: RedisRepository = mockk(relaxed = true)
    val mailSenderImpl: MailSenderImpl = mockk()

    val target = AuthServiceImpl(
        userReader = userReader,
        passwordEncoder = encoder,
        jwtTokenProvider = jwtTokenProvider,
        jwtProperties = jwtProperties,
        redisRepository = redisRepository,
        mailSender = mailSenderImpl,
    )

    val user = User(
        name = "이규진",
        nickname = "청출어람",
        email = "email",
        password = "password",
        profileImg = "추후 수정",
    )

    Given("로그인 성공 answer 정의") {

        val accessToken = "accessToken"
        val refreshToken = "refreshToken"

        every { userReader.findUserByEmail(any()) } returns user
        every { encoder.matches(any(), any()) } returns true
        every { jwtTokenProvider.createAccessToken(any()) } returns accessToken
        every { jwtTokenProvider.createRefreshToken(any()) } returns refreshToken

        val loginRequest = UserCommand.LoginRequest(
            email = "email",
            password = "password",
        )

        When("로그인 시") {
            val response = target.login(loginRequest)

            Then("성공시 토큰값이 반환 되어야 한다") {
                response.accessToken shouldBe accessToken
                response.refreshToken shouldBe refreshToken
            }
        }
    }

    Given("로그인 실패 answer 정의") {

        val accessToken = "accessToken"
        val refreshToken = "refreshToken"

        every { userReader.findUserByEmail(any()) } returns user
        every { encoder.matches(any(), any()) } returns false
        every { jwtTokenProvider.createAccessToken(any()) } returns accessToken
        every { jwtTokenProvider.createRefreshToken(any()) } returns refreshToken

        val loginRequest = UserCommand.LoginRequest(
            email = "wrongEmail",
            password = "wrongPassword",
        )

        When("로그인 시") {
            val exception = shouldThrow<PasswordMismatchException> {
                target.login(loginRequest)
            }

            Then("실패시 PASSWORD_MISMATCH 예외를 던져야 한다.") {
                exception.errorCode shouldBe UserErrorCode.PASSWORD_MISMATCH
            }
        }
    }
})