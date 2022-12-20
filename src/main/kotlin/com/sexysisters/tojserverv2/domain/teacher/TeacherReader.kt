package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherReader {
    fun search(schoolCode: String): List<Teacher>
    fun getTeacher(id: Long): Teacher
    fun getTeacher(id: Long, school: School): Teacher
}