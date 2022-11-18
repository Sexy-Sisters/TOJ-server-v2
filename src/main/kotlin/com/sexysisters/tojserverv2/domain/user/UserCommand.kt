package com.sexysisters.tojserverv2.domain.user

import org.springframework.security.crypto.password.PasswordEncoder

class UserCommand {
    data class CreateRequest(
        val name: String,
        val nickname: String,
        val email: String,
        var password: String,
    )
}

fun UserCommand.CreateRequest.toEntity() = User(
    name = name,
    nickname = nickname,
    email = email,
    password = password,
    profileImg = "추후 수정",
    authority = Authority.USER,
)

fun UserCommand.CreateRequest.setEncodedPassword(encoder: PasswordEncoder) {
    this.password = encoder.encode(this.password)
}