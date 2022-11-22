package com.sexysisters.tojserverv2.domain.user

import org.springframework.security.crypto.password.PasswordEncoder

class UserCommand {

    class CreateRequest(
        val email: String,
        var password: String,
        val nickname: String,
    )
}

fun UserCommand.CreateRequest.toEntity() = User(
    email = email,
    password = password,
    nickname = nickname,
    profileImg = "추후 수정",
)

fun UserCommand.CreateRequest.setEncodedPassword(encoder: PasswordEncoder) {
    this.password = encoder.encode(this.password)
}