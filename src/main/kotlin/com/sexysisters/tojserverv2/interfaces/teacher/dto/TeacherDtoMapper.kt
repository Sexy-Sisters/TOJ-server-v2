package com.sexysisters.tojserverv2.interfaces.teacher.dto

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface TeacherDtoMapper {

    fun of(request: TeacherRequest.Create): TeacherCommand.Create
    fun of(request: TeacherRequest.Update): TeacherCommand.Update

    @Mappings(
        value = [
            Mapping(source = "teacher.image.value", target = "image"),
            Mapping(source = "teacher.name.value", target = "name"),
            Mapping(source = "teacher.nickname.value", target = "nickname"),
            Mapping(source = "teacher.bio.value", target = "bio"),
        ]
    )
    fun of(teacher: Teacher): TeacherResponse.Main
}