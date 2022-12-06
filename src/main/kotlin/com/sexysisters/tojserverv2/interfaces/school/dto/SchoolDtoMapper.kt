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
    fun of(request: SchoolRequest.Search): SchoolCommand.Search

    // response
    fun of(info: SchoolInfo.Search): SchoolResponse.Search
}