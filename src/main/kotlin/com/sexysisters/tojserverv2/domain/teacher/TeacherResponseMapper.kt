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
}