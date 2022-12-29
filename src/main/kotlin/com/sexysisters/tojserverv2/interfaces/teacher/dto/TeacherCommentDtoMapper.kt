package com.sexysisters.tojserverv2.interfaces.teacher.dto

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentCommand
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface TeacherCommentDtoMapper {
    @Mappings(
        value = [
            Mapping(source = "content", target = "content"),
            Mapping(source = "anonymous", target = "anonymous")
        ]
    )
    fun of(request: TeacherCommentRequest.Main): TeacherCommentCommand.Main
}