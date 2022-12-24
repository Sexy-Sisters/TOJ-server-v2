package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.school.domain.Wallpaper
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
            Mapping(source = "wiki.name.value", target = "name"),
            Mapping(source = "wiki.html.value", target = "html"),
            Mapping(source = "wiki.markdown.value", target = "markdown"),
            Mapping(source = "wiki.views.value", target = "views"),
            Mapping(source = "wallpaper.value", target = "wallpaper")
        ]
    )
    fun of(wiki: Wiki, wallpaper: Wallpaper): WikiInfo.Main
}