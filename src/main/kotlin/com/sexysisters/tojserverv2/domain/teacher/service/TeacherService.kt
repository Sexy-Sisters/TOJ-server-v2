package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse

interface TeacherService {
    fun create(command: TeacherCommand.Create)
    fun getTeachers(schoolCode: String): List<TeacherResponse.Search>
    fun getTeacher(id: Long): TeacherResponse.Get
    fun update(id: Long, request: TeacherCommand.Update)
    fun delete(id: Long)
}