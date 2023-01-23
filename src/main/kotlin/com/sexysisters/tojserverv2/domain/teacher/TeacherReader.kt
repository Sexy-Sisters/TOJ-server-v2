package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import java.util.UUID

interface TeacherReader {
    fun search(schoolCode: String): List<Teacher>
    fun getTeacher(id: UUID): Teacher
    fun getTeacher(id: UUID, school: School): Teacher
}