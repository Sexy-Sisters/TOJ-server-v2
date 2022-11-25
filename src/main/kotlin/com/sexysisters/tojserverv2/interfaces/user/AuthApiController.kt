package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.service.AuthService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import com.sexysisters.tojserverv2.interfaces.user.dto.UserResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.websocket.server.PathParam

@Api(tags = ["Auth 관련 API"])
@RestController
@RequestMapping("api/v2/auth")
class AuthApiController(
    private val authService: AuthService,
) {
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @ApiOperation(value = "기본 로그인")
    @PostMapping
    fun login(@RequestBody @Valid request: UserRequest.Login): CommonResponse<UserResponse.Token> {
        val userCommand = mapper.of(request)
        val userInfo = authService.login(userCommand)
        val response = mapper.of(userInfo)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "로그아웃")
    @DeleteMapping
    fun logout(@RequestHeader("Authorization") accessToken: String) =
        authService.logout(accessToken)

    @ApiOperation(value = "구글 OAuth 로그인 링크")
    @GetMapping("/google")
    fun queryGoogleAuthLink(): CommonResponse<String> {
        val link = authService.getGoogleLink()
        return CommonResponse.success(link)
    }

    @ApiOperation(value = "Google OAuth callback (client 사용 안함)")
    @GetMapping("/google/callback")
    fun googleAuth(@PathParam("code") code: String): CommonResponse<UserResponse.Token> {
        val userInfo = authService.googleLogin(code)
        val response: UserResponse.Token = mapper.of(userInfo)
        return CommonResponse.success(response)
    }
}