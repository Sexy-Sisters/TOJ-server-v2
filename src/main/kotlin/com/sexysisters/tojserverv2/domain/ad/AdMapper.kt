package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface AdMapper {

    @Mappings(
        value = [
            Mapping(source = "link.value", target = "link"),
            Mapping(source = "image.value", target = "image"),
            Mapping(source = "expirationDate.value", target = "expirationDate"),
            Mapping(source = "costInfo.views.value", target = "views"),
            Mapping(source = "costInfo.clicks.value", target = "clicks"),
            Mapping(source = "company.companyName.value", target = "companyName"),
        ]
    )
    fun of(ad: Ad): AdInfo.Main
}