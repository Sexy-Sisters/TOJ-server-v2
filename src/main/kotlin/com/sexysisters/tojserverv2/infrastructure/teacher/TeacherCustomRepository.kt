package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import java.util.UUID

interface TeacherCustomRepository {
    fun getTeachersBySchoolCode(schoolCode: String): List<Teacher>
    fun findByIdAndSchoolCode(id: UUID, schoolCode: String): Teacher?
}