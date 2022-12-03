package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface SchoolMapper {
    fun of(neisSchoolResponse: NeisSchoolResponse): SchoolInfo.Search

    @Mapping(target = "applyStatus", source = "applyStatus")
    fun applyOf(applyStatus: String): SchoolInfo.Apply

    @Mapping(target = "applyStatus", source = "applyStatus")
    fun joinOf(applyStatus: String): SchoolInfo.Join
}