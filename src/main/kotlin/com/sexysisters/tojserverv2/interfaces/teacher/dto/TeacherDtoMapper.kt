package com.sexysisters.tojserverv2.interfaces.teacher.dto

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface TeacherDtoMapper {

    fun of(request: TeacherRequest.Create): TeacherCommand.Create
    fun of(request: TeacherRequest.Update): TeacherCommand.Update
}