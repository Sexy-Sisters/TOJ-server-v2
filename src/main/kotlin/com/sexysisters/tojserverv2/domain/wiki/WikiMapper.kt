package com.sexysisters.tojserverv2.domain.wiki

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface WikiMapper {
    fun of(wiki: Wiki): WikiInfo.Main
}