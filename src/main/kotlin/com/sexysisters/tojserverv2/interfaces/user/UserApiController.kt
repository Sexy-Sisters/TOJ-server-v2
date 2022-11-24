package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.application.user.UserFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.user.service.UserService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import com.sexysisters.tojserverv2.interfaces.user.dto.UserResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["유저 관련 API"])
@RestController
@RequestMapping("/api/v2/user")
class UserApiController(
    private val userFacade: UserFacade,
    private val userService: UserService,
) {
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @ApiOperation(value = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUpUser(
        @RequestBody @Valid request: UserRequest.SignUp
    ): CommonResponse<UserResponse.SignUp> {
        val userCommand = mapper.of(request)
        val userId = userFacade.createUser(userCommand)
        val response = mapper.of(userId)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "id로 프로필 정보 조회")
    @GetMapping("/{userId}")
    fun findUserProfile(
        @ApiParam(value = "유저 아이디") @PathVariable userId: Long
    ): CommonResponse<UserResponse.Profile> {
        val userInfo = userService.findUserProfile(userId)
        val response = mapper.of(userInfo)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "현재 로그인한 유저 프로필 정보 조회")
    @GetMapping
    fun findCurrentUserProfile(): CommonResponse<UserResponse.Profile> {
        val userInfo = userService.findCurrentUserProfile()
        val response = mapper.of(userInfo)
        return CommonResponse.success(response)
    }
}