package com.sexysisters.tojserverv2.interfaces.wiki.dto

import com.sexysisters.tojserverv2.domain.wiki.WikiCommand
import com.sexysisters.tojserverv2.domain.wiki.WikiInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface WikiDtoMapper {
    // request
    fun of(request: WikiRequest.Update): WikiCommand.Update

    // response
    fun of(wikiInfo: WikiInfo.Main): WikiResponse.Main
}