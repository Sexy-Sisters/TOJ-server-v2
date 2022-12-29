package com.sexysisters.tojserverv2.domain.teacherLike

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike

interface TeacherLikeStore {
    fun store(teacherLike: TeacherLike): TeacherLike
    fun delete(teacher: Teacher, student: Student)
}