package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import java.util.*

interface TeacherService {
    fun create(command: TeacherCommand.Create)
    fun getTeachers(schoolCode: String): List<TeacherResponse.Main>
    fun getTeacher(id: UUID): TeacherResponse.Main
    fun update(id: UUID, command: TeacherCommand.Update)
    fun delete(id: UUID)
}