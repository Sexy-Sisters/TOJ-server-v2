package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherReader {
    fun search(schoolCode: String): List<Teacher>
}