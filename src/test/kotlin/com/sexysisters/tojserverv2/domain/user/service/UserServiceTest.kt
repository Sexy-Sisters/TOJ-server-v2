package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.Authority
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest : BehaviorSpec({

    val user = User(
        id = 1L,
        name = "이규진",
        nickname = "청출어람",
        email = "leekujin14@gmail.com",
        password = "enCodedPassword",
        profileImg = "추후 수정",
        authority = Authority.USER,
    )

    Given("모의 객체 생성") {
        val encoder: PasswordEncoder = mockk()
        val userStore: UserStore = mockk()

        val userCapture = slot<User>()

        val target = UserServiceImpl(
            userStore,
            encoder,
        )

        every { encoder.encode(any()) } returns "encodedPassword"
        every { userStore.store(capture(userCapture)) } returns user

        When("회원가입 시") {
            val createRequest = UserCommand.CreateRequest(
                name = "이규진",
                nickname = "청출어람",
                email = "leekujin14@gmail.com",
                password = "password",
            )

            val userId = target.createUser(createRequest)

            Then("Command가 정상적으로 Entity로 변환되어야 한다.") {
                val userEntity = userCapture.captured

                userEntity.email shouldBe createRequest.email
                userEntity.name shouldBe createRequest.name
                userEntity.nickname shouldBe createRequest.nickname
                userEntity.password shouldBe "encodedPassword"
                userEntity.authority shouldBe Authority.USER
                userEntity.profileImg shouldBe "추후 수정"
            }

            Then("PasswordEncoder와 UserStore 로직이 동작해야 한다.") {
                verify(exactly = 1) { encoder.encode(any()) }
                verify(exactly = 1) { userStore.store(any()) }
            }
        }
    }
})