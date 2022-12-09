package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.service.OAuthService
import com.sexysisters.tojserverv2.interfaces.auth.dto.AuthDtoMapper
import com.sexysisters.tojserverv2.interfaces.auth.dto.AuthResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@Api(tags = ["OAuth 관련 API"])
@RestController
@RequestMapping("/api/v2/oauth")
class OAuthApiController(
    private val oAuthService: OAuthService,
    private val authDtoMapper: AuthDtoMapper,
) {

    @ApiOperation(value = "구글 OAuth 로그인 링크")
    @GetMapping("/google")
    fun queryGoogleAuthLink(): CommonResponse<String> {
        val link = oAuthService.getGoogleLink()
        return CommonResponse.success(link)
    }

    @ApiOperation(value = "Google OAuth callback (client 사용 안함)")
    @GetMapping("/google/callback")
    fun googleAuth(@PathParam("code") code: String): CommonResponse<AuthResponse.Token> {
        val userInfo = oAuthService.googleLogin(code)
        val response = authDtoMapper.of(userInfo)
        return CommonResponse.success(response)
    }
}