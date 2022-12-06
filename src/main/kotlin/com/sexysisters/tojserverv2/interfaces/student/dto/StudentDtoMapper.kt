package com.sexysisters.tojserverv2.interfaces.student.dto

import com.sexysisters.tojserverv2.domain.student.StudentCommand
import com.sexysisters.tojserverv2.domain.student.StudentInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface StudentDtoMapper {

    fun of(info: StudentInfo.Main): StudentResponse.Main
    fun of(request: StudentRequest.Create): StudentCommand.Create
}