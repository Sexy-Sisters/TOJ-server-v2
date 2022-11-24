package com.sexysisters.tojserverv2.domain.user

class UserInfo {

    class Profile(
        val nickname: String,
        val profileImg: String,
    )

    class Token(
        val accessToken: String,
        val refreshToken: String,
    )
}