package com.sexysisters.tojserverv2.infrastructure.teacherLike

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherLikeRepository : JpaRepository<TeacherLike, String> {
    fun existsByTeacherAndStudent(teacher: Teacher, student: Student): Boolean
    fun deleteByTeacherAndStudent(teacher: Teacher, student: Student)
}