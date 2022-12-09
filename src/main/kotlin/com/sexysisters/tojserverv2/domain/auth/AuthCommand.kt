package com.sexysisters.tojserverv2.domain.auth

class AuthCommand {

    class LoginRequest(
        val email: String,
        val password: String,
    )

    class SendCodeRequest(
        val email: String,
    )

    class AuthenticateCode(
        val email: String,
        val code: String,
    )
}