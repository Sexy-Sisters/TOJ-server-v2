package com.sexysisters.tojserverv2.domain.user

class UserInfo {

    class Profile(
        val email: String,
        val nickname: String,
        val profileImg: String,
        val name: String,
    )

    class Token(
        val accessToken: String,
        val refreshToken: String? = null,
    )
}