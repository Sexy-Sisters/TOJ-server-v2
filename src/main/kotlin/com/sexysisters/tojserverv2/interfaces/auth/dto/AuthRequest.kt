package com.sexysisters.tojserverv2.interfaces.auth.dto

import javax.validation.constraints.NotBlank

class AuthRequest {

    data class Login(
        @field:NotBlank(message = "email is empty")
        val email: String,

        @field:NotBlank(message = "password is empty")
        val password: String,
    )

    data class SendCode(
        @field:NotBlank(message = "email is empty")
        val email: String,
    )

    data class AuthenticateCode(
        @field:NotBlank(message = "email is empty")
        val email: String,

        @field:NotBlank(message = "code is empty")
        val code: String,
    )
}