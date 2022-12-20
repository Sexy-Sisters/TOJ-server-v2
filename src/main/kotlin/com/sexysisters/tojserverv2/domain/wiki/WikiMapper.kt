package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface WikiMapper {

    @Mappings(
        value = [
            Mapping(source = "name.value", target = "name"),
            Mapping(source = "html.value", target = "html"),
            Mapping(source = "markdown.value", target = "markdown"),
            Mapping(source = "views.value", target = "views"),
        ]
    )
    fun of(wiki: Wiki): WikiInfo.Main
}