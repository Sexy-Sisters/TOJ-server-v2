package com.sexysisters.tojserverv2.domain.user

class UserInfo {

    class Profile(
        val nickname: String,
        val profileImg: String,
    )

    class TokenResponse(
        val accessToken: String,
        val refreshToken: String,
    )
}