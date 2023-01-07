package com.sexysisters.tojserverv2.interfaces.user.dto

class UserResponse {

    class SignUp(
        val userId: String,
    )

    class Profile(
        val nickname: String,
        val profileImg: String,
    )
}