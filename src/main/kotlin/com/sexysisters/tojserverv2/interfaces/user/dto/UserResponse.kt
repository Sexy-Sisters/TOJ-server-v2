package com.sexysisters.tojserverv2.interfaces.user.dto

class UserResponse {

    class SignUp(
        val userId: Long,
    )

    class Profile(
        val nickname: String,
        val profileImg: String,
    )
}