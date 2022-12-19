package com.sexysisters.tojserverv2.domain.student

import com.sexysisters.tojserverv2.domain.student.domain.Student
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
interface StudentMapper {

    @Mappings(
        value = [
            Mapping(source = "user.profileImg", target = "profileImg"),
            Mapping(source = "user.nickname", target = "nickname"),
        ]
    )
    fun of(student: Student): StudentInfo.Main
}