package com.sexysisters.tojserverv2.domain.user

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

    class GoogleLoginRequest(
        val code: String,
    )

    class UpdateRequest(
        val id: Long,
        val nickname: String,
        val name: String
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