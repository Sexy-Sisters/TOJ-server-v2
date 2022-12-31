package com.sexysisters.tojserverv2.domain.teacherLike

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherLikeReader {
    fun hasTeacherLike(teacher: Teacher, student: Student): Boolean
}