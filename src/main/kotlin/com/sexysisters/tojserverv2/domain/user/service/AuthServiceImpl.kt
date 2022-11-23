package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.oauth.GoogleAuthExecutor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val userStore: UserStore,
    private val googleAuthExecutor: GoogleAuthExecutor,
    private val jwtTokenProvider: JwtTokenProvider,
) : AuthService {

    override fun getGoogleLink() = googleAuthExecutor.getLink()

    @Transactional
    override fun googleLogin(command: UserCommand.GoogleLoginRequest): UserInfo.TokenResponse {
        val code = command.code
        val oAuthResponse = googleAuthExecutor.execute(code)

        val user = User(
            nickname = oAuthResponse.name,
            email = oAuthResponse.email,
            profileImg = oAuthResponse.picture,
            password = "OAUTH"
        )

        userStore.storeOAuthUser(user)

        return UserInfo.TokenResponse(
            accessToken = jwtTokenProvider.createAccessToken(user.email),
            refreshToken = jwtTokenProvider.createRefreshToken(user.email),
        )
    }
}