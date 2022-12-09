package com.sexysisters.tojserverv2.interfaces.auth.dto

import com.sexysisters.tojserverv2.domain.auth.AuthCommand
import com.sexysisters.tojserverv2.domain.auth.AuthInfo
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
    fun of(request: AuthRequest.Login): AuthCommand.LoginRequest

    fun of(request: AuthRequest.SendCode): AuthCommand.SendCodeRequest

    fun of(request: AuthRequest.AuthenticateCode): AuthCommand.AuthenticateCode

    // response

    fun of(userInfo: AuthInfo.Token): AuthResponse.Token
}