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
            Mapping(source = "image.value", target = "image"),
            Mapping(source = "name.value", target = "name"),
            Mapping(source = "nickname.value", target = "nickname"),
            Mapping(source = "bio.value", target = "bio"),
        ]
    )
    fun of(teacher: Teacher): TeacherResponse.Main
}