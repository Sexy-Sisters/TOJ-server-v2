package com.sexysisters.tojserverv2.interfaces.auth.dto

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
interface AuthDtoMapper {

    // request
    fun of(request: AuthRequest.Login): UserCommand.LoginRequest

    fun of(request: AuthRequest.SendCode): UserCommand.SendCodeRequest

    fun of(request: AuthRequest.AuthenticateCode): UserCommand.AuthenticateCode

    // response

    fun of(userInfo: UserInfo.Token): AuthResponse.Token
}
