package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface TeacherResponseMapper {

    @Mappings(
        value = [
            Mapping(source = "teacher.image.value", target = "image"),
            Mapping(source = "teacher.name.value", target = "name"),
            Mapping(source = "teacher.nickname.value", target = "nickname"),
            Mapping(source = "teacher.bio.value", target = "bio"),
        ]
    )
    fun of(teacher: Teacher): TeacherResponse.Search

    @Mappings(
        value = [
            Mapping(source = "teacher.image.value", target = "image"),
            Mapping(source = "teacher.name.value", target = "name"),
            Mapping(source = "teacher.nickname.value", target = "nickname"),
            Mapping(source = "teacher.bio.value", target = "bio"),
        ]
    )
    fun ofDetail(teacher: Teacher): TeacherResponse.Get
}