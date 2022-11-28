package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.oauth.GoogleAuthExecutor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthServiceImpl(
    private val googleAuthExecutor: GoogleAuthExecutor,
    private val userStore: UserStore,
    private val jwtTokenProvider: JwtTokenProvider,
) : OAuthService {

    override fun getGoogleLink() = googleAuthExecutor.getLink()

    @Transactional
    override fun googleLogin(code: String): UserInfo.Token {
        val oAuthResponse = googleAuthExecutor.execute(code)
        val user = User(
            nickname = oAuthResponse.name,
            email = oAuthResponse.email,
            profileImg = oAuthResponse.picture,
            password = "OAUTH"
        )
        userStore.storeOAuthUser(user)

        return UserInfo.Token(
            accessToken = jwtTokenProvider.createAccessToken(user.email),
            refreshToken = jwtTokenProvider.createRefreshToken(user.email),
        )
    }
}