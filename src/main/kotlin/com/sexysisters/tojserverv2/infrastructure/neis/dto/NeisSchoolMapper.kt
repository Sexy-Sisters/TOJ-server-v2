package com.sexysisters.tojserverv2.infrastructure.neis.dto

import com.sexysisters.tojserverv2.common.util.api.neis.dto.Row
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
interface NeisSchoolMapper {

    // Mapping(source = "ENG_SCHUL_NM", target = "englishName"),
    @Mappings(
        value = [
            Mapping(source = "SD_SCHUL_CODE", target = "code"),
            Mapping(source = "ATPT_OFCDC_SC_NM", target = "beString"),
            Mapping(source = "SCHUL_NM", target = "name"),
            Mapping(source = "SCHUL_KND_SC_NM", target = "division"),
            Mapping(source = "ORG_RDNMA", target = "address"),
            Mapping(source = "FOAS_MEMRD", target = "birthday"),
            Mapping(source = "HMPG_ADRES", target = "homePageAddress"),
            Mapping(source = "ORG_TELNO", target = "phone"),
            Mapping(source = "HS_SC_NM", target = "kind"),
        ]
    )
    fun of(row: Row): NeisSchoolResponse
}