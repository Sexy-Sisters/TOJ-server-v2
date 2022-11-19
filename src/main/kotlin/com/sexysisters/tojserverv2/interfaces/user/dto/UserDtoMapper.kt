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

    fun of(request: UserRequest.Create): UserCommand.CreateRequest

    fun of(userId: Long?): UserResponse.CreateUser

    fun of(userInfo: UserInfo.Profile): UserResponse.Profile
}