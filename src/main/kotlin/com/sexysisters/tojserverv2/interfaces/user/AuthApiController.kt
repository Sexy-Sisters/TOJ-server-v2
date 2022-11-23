package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.application.user.AuthFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.service.AuthService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@Api(tags = ["Auth 관련 API"])
@RestController
@RequestMapping("api/v2/auth")
class AuthApiController(
    private val authFacade: AuthFacade,
    private val authService: AuthService,
) {
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @ApiOperation(value = "구글 OAuth 로그인 링크")
    @GetMapping("/google")
    fun queryGoogleAuthLink(): CommonResponse<String> {
        val response = authService.getGoogleLink()
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "구글 콜백")
    @GetMapping("/google/callback")
    fun callback(@PathParam("code") code: String) = code

    @ApiOperation(value = "회원가입")
    @PostMapping("/google")
    fun googleAuth(@RequestBody request: UserRequest.GoogleAuth): CommonResponse<UserInfo.TokenResponse> {
        val userCommand = mapper.of(request)
        val response = authFacade.googleLogin(userCommand)
        return CommonResponse.success(response)
    }
}