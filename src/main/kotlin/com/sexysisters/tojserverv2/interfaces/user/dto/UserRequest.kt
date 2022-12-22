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

    data class UpdateProfileImg(
        @field:NotBlank(message = "img url is empty")
        val profileImg: String,
    )

    data class Update(
        @field:NotBlank(message = "nickname is emtpy")
        val nickname: String,

        @field:NotBlank(message = "name is empty")
        val name: String,
    )
}