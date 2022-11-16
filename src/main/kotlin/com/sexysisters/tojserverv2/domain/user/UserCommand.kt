package com.sexysisters.tojserverv2.domain.user

class UserCommand {
    data class CreateRequest (
        val name: String,
        val nickname: String,
        val email: String,
        val password: String,
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