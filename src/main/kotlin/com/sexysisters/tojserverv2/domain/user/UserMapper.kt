package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.user.domain.User
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface UserMapper {

    @Mappings(
        value = [
            Mapping(source = "email.value", target = "email"),
            Mapping(source = "nickname.value", target = "nickname"),
            Mapping(source = "image.value", target = "profileImg"),
            Mapping(source = "name.value", target = "name"),
        ]
    )
    fun of(user: User): UserInfo.Profile
}