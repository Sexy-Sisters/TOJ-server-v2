package com.sexysisters.tojserverv2.infrastructure.teacherLike

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacherLike.TeacherLikeStore
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike
import org.springframework.stereotype.Component

@Component
class TeacherLikeStoreImpl(
    private val teacherLikeRepository: TeacherLikeRepository
): TeacherLikeStore {

    override fun store(teacherLike: TeacherLike) =
        teacherLikeRepository.save(teacherLike)

    override fun delete(teacher: Teacher, student: Student) =
        teacherLikeRepository.deleteByTeacherAndStudent(teacher, student)
}