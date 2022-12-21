package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherCustomRepository {
    fun getTeachersBySchoolCode(schoolCode: String): List<Teacher>
    fun findByIdAndSchoolCode(id: Long, schoolCode: String): Teacher?
}