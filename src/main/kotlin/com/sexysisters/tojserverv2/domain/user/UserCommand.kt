package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.config.properties.S3Properties
import com.sexysisters.tojserverv2.domain.user.type.Nickname
import org.springframework.security.crypto.password.PasswordEncoder

class UserCommand {
    class CreateRequest(
        val email: String,
        var password: String,
        val nickname: String,
    )

    class UpdateRequest(
        val nickname: String,
        val name: String
    )

    class UpdateProfileImgRequest(
        val profileImg: String,
    )
}

private val DEFAULT_NAME = "이름"

fun UserCommand.CreateRequest.toEntity() = User(
    email = Email(email),
    password = Password(password),
    nickname = Nickname(nickname),
    image = Image(S3Properties.defaultProfileImg),
    name = Name(DEFAULT_NAME),
)

fun UserCommand.CreateRequest.setEncodedPassword(encoder: PasswordEncoder) {
    this.password = encoder.encode(this.password)
}