package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.common.util.api.oauth.dto.OAuthInfoResponse
import com.sexysisters.tojserverv2.domain.auth.AuthInfo
import com.sexysisters.tojserverv2.domain.user.UserStore
import com.sexysisters.tojserverv2.domain.user.domain.*
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
        val initUser = createUser(oAuthResponse)
        userStore.storeOAuthUser(initUser)
        return AuthInfo.Token(
            accessToken = jwtTokenProvider.createAccessToken(initUser.emailValue()),
            refreshToken = jwtTokenProvider.createRefreshToken(initUser.emailValue()),
        )
    }

    private fun createUser(response: OAuthInfoResponse) = User(
        nickname = Nickname(response.name),
        email = Email(response.email),
        image = Image(response.picture),
        password = Password("OAUTH"),
        name = Name("이름")
    )
}