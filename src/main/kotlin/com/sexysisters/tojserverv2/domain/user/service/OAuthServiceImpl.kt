package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.auth.AuthInfo
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserStore
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.oauth.OAuthExecutor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthServiceImpl(
    private val googleAuthExecutor: OAuthExecutor,
    private val userStore: UserStore,
    private val jwtTokenProvider: JwtTokenProvider,
) : OAuthService {

    override fun getGoogleLink() = googleAuthExecutor.getLink()

    @Transactional
    override fun googleLogin(code: String): AuthInfo.Token {
        val oAuthResponse = googleAuthExecutor.execute(code)
        val initUser = User(
            nickname = oAuthResponse.name,
            email = oAuthResponse.email,
            profileImg = oAuthResponse.picture,
            password = "OAUTH",
            name = "이름"
        )
        userStore.storeOAuthUser(initUser)

        return AuthInfo.Token(
            accessToken = jwtTokenProvider.createAccessToken(initUser.email),
            refreshToken = jwtTokenProvider.createRefreshToken(initUser.email),
        )
    }
}