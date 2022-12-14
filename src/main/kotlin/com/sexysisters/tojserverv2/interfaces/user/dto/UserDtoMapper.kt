package com.sexysisters.tojserverv2.interfaces.user.dto

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface UserDtoMapper {

    // request
    fun of(request: UserRequest.SignUp): UserCommand.CreateRequest

    fun of(request: UserRequest.Update): UserCommand.UpdateRequest

    fun of(request: UserRequest.UpdateProfileImg): UserCommand.UpdateProfileImgRequest

    // response
    fun of(userId: Long?): UserResponse.SignUp

    fun of(userInfo: UserInfo.Profile): UserResponse.Profile
}