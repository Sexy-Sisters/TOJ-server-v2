package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse

interface TeacherService {
    fun create(command: TeacherCommand.Create)
    fun getTeachers(schoolCode: String): List<TeacherResponse.Main>
    fun getTeacher(id: Long): TeacherResponse.Main
    fun update(id: Long, command: TeacherCommand.Update)
    fun delete(id: Long)
}