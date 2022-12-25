package com.sexysisters.tojserverv2.interfaces.school.dto

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface SchoolDtoMapper {
    // request
    fun of(request: SchoolRequest.UpdateWallpaper): SchoolCommand.UpdateWallpaper

    // response
    fun of(info: SchoolInfo.Search): SchoolResponse.Search
}