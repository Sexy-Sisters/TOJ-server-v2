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
            Mapping(source = "costInfo.views", target = "views"),
            Mapping(source = "company.name", target = "companyName")
        ]
    )
    fun of(ad: Ad): AdInfo.Main
}