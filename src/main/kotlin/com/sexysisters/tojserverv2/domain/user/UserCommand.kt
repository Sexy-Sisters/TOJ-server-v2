package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.config.properties.S3Properties
import org.springframework.security.crypto.password.PasswordEncoder

class UserCommand {

    class CreateRequest(
        val email: String,
        var password: String,
        val nickname: String,
    )

    class LoginRequest(
        val email: String,
        val password: String,
    )

    class UpdateRequest(
        val nickname: String,
        val name: String
    )

    class UpdateProfileImgRequest(
        val profileImg: String,
    )

    class SendCodeRequest(
        val email: String,
    )

    class AuthenticateCode(
        val email: String,
        val code: String,
    )
}

fun UserCommand.CreateRequest.toEntity() = User(
    email = email,
    password = password,
    nickname = nickname,
    profileImg = S3Properties.defaultProfileImg,

)

fun UserCommand.CreateRequest.setEncodedPassword(encoder: PasswordEncoder) {
    this.password = encoder.encode(this.password)
}