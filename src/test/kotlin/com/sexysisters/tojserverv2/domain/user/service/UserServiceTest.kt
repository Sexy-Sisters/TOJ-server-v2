package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.config.properties.S3Properties
import com.sexysisters.tojserverv2.domain.user.Authority
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest : BehaviorSpec({

    val encoder: PasswordEncoder = mockk()
    val userStore: UserStore = mockk()
    val userReader: UserReader = mockk()

    val target = UserServiceImpl(
        userStore = userStore,
        userReader = userReader,
        passwordEncoder = encoder,
    )

    val user = User(
        name = "이규진",
        nickname = "청출어람",
        email = "leekujin14@gmail.com",
        password = "enCodedPassword",
        profileImg = "추후 수정",
    )

    Given("회원가입 answer 정의 & parameter capture") {
        val userCapture = slot<User>()

        every { encoder.encode(any()) } returns "encodedPassword"
        every { userStore.store(capture(userCapture)) } returns user

        When("회원가입 시") {
            val createRequest = UserCommand.CreateRequest(
                nickname = "청출어람",
                email = "leekujin14@gmail.com",
                password = "password",
            )

            val userId = target.createUser(createRequest)

            Then("Command가 정상적으로 Entity로 변환되어야 한다.") {
                val userEntity = userCapture.captured

                userEntity.email shouldBe createRequest.email
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

    Given("프로필 조회 answer 정의 & parameter capture") {
        val userId = 1L

        val userIdCapture = slot<Long>()
        every { userReader.findUserById(capture(userIdCapture)) } returns user

        When("특정 유저 프로필 조회시") {
            val userInfo = target.findUserProfile(userId)

            Then("정확한 유저 프로필이 조회되어야 한다.") {

                userIdCapture.captured shouldBe userId
                userInfo.email shouldBe user.email
                userInfo.nickname shouldBe user.nickname
                userInfo.profileImg shouldBe user.profileImg
                // TODO :: add properties -> description, age, school, ...
            }

            Then("UserReader 로직이 동작해야 한다.") {
                verify(exactly = 1) { userReader.findUserById(userId) }
            }
        }
    }

    Given("현재 로그인한 유저 조회") {
        every { userReader.getCurrentUser() } returns user

        When("성공") {
            val userInfo = target.findCurrentUserProfile()

            Then("정확한 값이 반환되어야 한다.") {
                userInfo.email shouldBe user.email
                userInfo.nickname shouldBe user.nickname
                userInfo.profileImg shouldBe user.profileImg
            }
        }
    }

    Given("유저 정보 수정") {
        every { userReader.getCurrentUser() } returns user

        val request = UserCommand.UpdateRequest(
            nickname = "이미청출어람해버림",
            name = "이름",
        )

        When("성공") {
            val userInfo = target.updateUser(request)

            Then("유정 정보가 정확하게 변경되야함") {
                userInfo.nickname shouldBe request.nickname
                userInfo.name shouldBe request.name
            }
        }
    }
})