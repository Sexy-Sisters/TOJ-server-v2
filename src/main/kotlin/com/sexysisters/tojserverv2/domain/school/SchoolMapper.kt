package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.school.student.Student
import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse
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
interface SchoolMapper {
    fun of(neisSchoolResponse: NeisSchoolResponse): SchoolInfo.Search

    @Mappings(
        value = [
            Mapping(source = "user.profileImg", target = "profileImg"),
            Mapping(source = "user.nickname", target = "nickname"),
        ]
    )
    fun of(student: Student): SchoolInfo.Student
}