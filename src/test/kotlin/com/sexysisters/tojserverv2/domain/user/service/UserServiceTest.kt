package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.config.properties.S3Properties
import com.sexysisters.tojserverv2.domain.user.*
import com.sexysisters.tojserverv2.domain.user.domain.*
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder

val encoder: PasswordEncoder = mockk()
val userStore: UserStore = mockk()
val userReader: UserReader = mockk()
val userMapper = Mappers.getMapper(UserMapper::class.java)

val target = UserServiceImpl(
    userStore = userStore,
    userReader = userReader,
    passwordEncoder = encoder,
    userMapper = userMapper,
)

val user = User(
    name = Name("이규진"),
    nickname = Nickname("청출어람"),
    email = Email("leekujin14@gmail.com"),
    password = Password("enCodedPassword"),
    image = Image(S3Properties.defaultProfileImg)
)

class UserServiceTest : BehaviorSpec({

    Given("회원가입") {
        val userCapture = slot<User>()

        every { encoder.encode(any()) } returns "encodedPassword"
        every { userStore.store(capture(userCapture)) } returns user

        When("성공") {
            val createRequest = UserCommand.CreateRequest(
                nickname = "청출어람",
                email = "leekujin14@gmail.com",
                password = "password",
            )

            target.createUser(createRequest)

            Then("Command가 정상적으로 Entity로 변환되어야 한다.") {
                val userEntity = userCapture.captured

                userEntity.emailValue() shouldBe createRequest.email
                userEntity.nicknameValue() shouldBe createRequest.nickname
                userEntity.passwordValue() shouldBe "encodedPassword"
                userEntity.authority shouldBe Authority.USER
                userEntity.imageValue() shouldBe S3Properties.defaultProfileImg
            }

            Then("PasswordEncoder와 UserStore 로직이 동작해야 한다.") {
                verify(exactly = 1) { encoder.encode(any()) }
                verify(exactly = 1) { userStore.store(any()) }
            }
        }
    }

    Given("아이디로 프로필 조회") {
        val userId = 1L

        val userIdCapture = slot<Long>()
        every { userReader.getUser(capture(userIdCapture)) } returns user

        When("특정 유저 프로필 조회시") {
            val userInfo = target.findUserProfile(userId)

            Then("정확한 유저 프로필이 조회되어야 한다.") {

                userIdCapture.captured shouldBe userId
                userInfo.email shouldBe user.emailValue()
                userInfo.nickname shouldBe user.nicknameValue()
                userInfo.profileImg shouldBe user.imageValue()
                userInfo.name shouldBe user.nameValue()
                // TODO :: add properties -> description, age, school, ...
            }

            Then("UserReader 로직이 동작해야 한다.") {
                verify(exactly = 1) { userReader.getUser(userId) }
            }
        }
    }

    Given("현재 로그인한 유저 조회") {
        every { userReader.getCurrentUser() } returns user

        When("성공") {
            val userInfo = target.findCurrentUserProfile()

            Then("정확한 값이 반환되어야 한다.") {
                userInfo.email shouldBe user.emailValue()
                userInfo.nickname shouldBe user.nicknameValue()
                userInfo.profileImg shouldBe user.imageValue()
                userInfo.name shouldBe user.nameValue()
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