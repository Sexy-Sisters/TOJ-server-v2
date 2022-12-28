package com.sexysisters.tojserverv2.interfaces.feed.dto

import com.sexysisters.tojserverv2.domain.feed.FeedCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface FeedDtoMapper {

    // request
    fun of(request: FeedRequest.Create): FeedCommand.Create
    fun of(request: FeedRequest.Update): FeedCommand.Update
}