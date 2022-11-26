package com.sexysisters.tojserverv2.interfaces.user.dto

import javax.validation.constraints.NotBlank

class UserRequest {

    data class SignUp(
        @field:NotBlank(message = "email is empty")
        val email: String,

        @field:NotBlank(message = "password is empty")
        val password: String,

        @field:NotBlank(message = "nickname is empty")
        val nickname: String,
    )

    data class Login(
        @field:NotBlank(message = "email is empty")
        val email: String,

        @field:NotBlank(message = "password is empty")
        val password: String,
    )

    data class GoogleAuth(
        @field:NotBlank(message = "auth code is empty")
        val code: String,
    )

    data class UpdateProfileImg(
        @field:NotBlank(message = "img url is empty")
        val imgUrl: String,
    )
}