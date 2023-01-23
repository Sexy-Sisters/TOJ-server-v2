package com.sexysisters.tojserverv2.infrastructure.teacherLike

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacherLike.TeacherLikeReader
import org.springframework.stereotype.Component

@Component
class TeacherLikeReaderImpl(
    private val teacherLikeRepository: TeacherLikeRepository
) : TeacherLikeReader {

    override fun hasTeacherLike(teacher: Teacher, student: Student) =
        teacherLikeRepository.existsByTeacherAndStudent(teacher, student)
}