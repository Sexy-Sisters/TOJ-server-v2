package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherCustomRepository {
    fun search(schoolCode: String): List<Teacher>
    fun get(id: Long, schoolCode: String): Teacher?
}