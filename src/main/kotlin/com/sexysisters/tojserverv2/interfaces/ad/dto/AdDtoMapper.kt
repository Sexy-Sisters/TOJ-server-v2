package com.sexysisters.tojserverv2.interfaces.ad.dto

import com.sexysisters.tojserverv2.domain.ad.AdCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface AdDtoMapper {
    // request
    fun of(request: AdRequest.Create): AdCommand.Create
}