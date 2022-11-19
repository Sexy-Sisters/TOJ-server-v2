package com.sexysisters.tojserverv2.domain.user

class UserInfo {

    class Profile(
        val nickname: String,
        val profileImg: String,
    ) {
        companion object {
            fun of(user: User) = Profile(
                nickname = user.nickname,
                profileImg = user.profileImg,
            )
        }
    }
}