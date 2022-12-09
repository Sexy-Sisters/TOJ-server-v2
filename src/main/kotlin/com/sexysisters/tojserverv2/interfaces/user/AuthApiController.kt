package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.service.AuthService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import com.sexysisters.tojserverv2.interfaces.user.dto.UserResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["Auth 관련 API"])
@RestController
@RequestMapping("api/v2/auth")
class AuthApiController(
    private val authService: AuthService,
    private val userDtoMapper: UserDtoMapper,
) {

    @ApiOperation(value = "기본 로그인")
    @PostMapping
    fun login(@RequestBody @Valid request: UserRequest.Login): CommonResponse<UserResponse.Token> {
        val userCommand = userDtoMapper.of(request)
        val userInfo = authService.login(userCommand)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "로그아웃")
    @DeleteMapping
    fun logout(@RequestHeader("Authorization") accessToken: String) =
        authService.logout(accessToken)


    @ApiOperation(value = "액세스 토큰 재발급")
    @PutMapping
    fun getNewAccessToken(@RequestHeader(value = "Refresh-Token") refreshToken: String): CommonResponse<UserResponse.Token> {
        val accessToken = authService.getNewAccessToken(refreshToken)
        val response = UserResponse.Token(accessToken)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "인증 코드 이메일 발송")
    @PostMapping("/code")
    fun sendCode(@RequestBody @Valid request: UserRequest.SendCode) {
        val userCommand = userDtoMapper.of(request)
        authService.sendCode(userCommand)
    }

    @ApiOperation(value = "발송된 인증 코드 검증")
    @DeleteMapping("/code")
    fun authenticateCode(@RequestBody @Valid request: UserRequest.AuthenticateCode): CommonResponse<Boolean> {
        val userCommand = userDtoMapper.of(request)
        val response = authService.authenticateCode(userCommand)
        return CommonResponse.success(response)
    }
}