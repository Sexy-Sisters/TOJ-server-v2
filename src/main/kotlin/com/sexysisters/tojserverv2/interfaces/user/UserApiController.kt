package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.application.user.UserFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import com.sexysisters.tojserverv2.interfaces.user.dto.UserResponse
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/user")
class UserApiController(
    private val userFacade: UserFacade,
) {
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @PostMapping
    fun signUpUser(@RequestBody @Valid request: UserRequest.SignUp): CommonResponse<UserResponse.SignUP> {
        val userCommand = mapper.of(request)
        val userId = userFacade.createUser(userCommand)
        val response = mapper.of(userId)
        return CommonResponse.success(response)
    }
}