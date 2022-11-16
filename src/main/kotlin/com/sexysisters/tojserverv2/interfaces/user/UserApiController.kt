package com.sexysisters.tojserverv2.interfaces.user

import com.sexysisters.tojserverv2.application.user.UserFacade
import com.sexysisters.tojserverv2.domain.user.service.UserService
import com.sexysisters.tojserverv2.interfaces.user.dto.UserDtoMapper
import com.sexysisters.tojserverv2.interfaces.user.dto.UserRequest
import org.mapstruct.factory.Mappers
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/user")
class UserApiController (
    private val userFacade: UserFacade,
){
    private val mapper = Mappers.getMapper(UserDtoMapper::class.java)

    @PostMapping
    fun registerUser(@RequestBody @Valid request: UserRequest.Create) {
        val userCommand = mapper.of(request)
        userFacade.createUser(userCommand)
    }
}