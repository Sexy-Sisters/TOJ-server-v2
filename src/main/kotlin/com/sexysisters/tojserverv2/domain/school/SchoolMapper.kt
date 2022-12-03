package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface SchoolMapper {
    fun of(neisSchoolResponse: NeisSchoolResponse): SchoolInfo.Search
}