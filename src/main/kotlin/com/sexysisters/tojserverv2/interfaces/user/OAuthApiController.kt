package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.service.OAuthService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserResponse
import io.swagger.annotations.ApiOperation
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/api/v2/oauth")
class OAuthApiController(
    private val oAuthService: OAuthService,
) {
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @ApiOperation(value = "구글 OAuth 로그인 링크")
    @GetMapping("/google")
    fun queryGoogleAuthLink(): CommonResponse<String> {
        val link = oAuthService.getGoogleLink()
        return CommonResponse.success(link)
    }

    @ApiOperation(value = "Google OAuth callback (client 사용 안함)")
    @GetMapping("/google/callback")
    fun googleAuth(@PathParam("code") code: String): CommonResponse<UserResponse.Token> {
        val userInfo = oAuthService.googleLogin(code)
        val response: UserResponse.Token = mapper.of(userInfo)
        return CommonResponse.success(response)
    }
}