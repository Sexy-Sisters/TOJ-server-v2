package com.sexysisters.tojserverv2.domain.teacherLike.service

import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.teacher.TeacherReader
import com.sexysisters.tojserverv2.domain.teacherLike.TeacherLikeReader
import com.sexysisters.tojserverv2.domain.teacherLike.TeacherLikeStore
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class TeacherLikeServiceImpl(
    private val teacherReader: TeacherReader,
    private val studentReader: StudentReader,
    private val teacherLikeReader: TeacherLikeReader,
    private val teacherLikeStore: TeacherLikeStore
) : TeacherLikeService {

    override fun like(teacherId: UUID): Boolean {
        val teacher = teacherReader.getTeacher(teacherId)
        val student = studentReader.getCurrentStudent()

        if (teacherLikeReader.hasTeacherLike(teacher, student)) {
            teacherLikeStore.delete(teacher, student)
            return true
        }
        val initTeacherLike = TeacherLike(
            teacher,
            student
        )
        teacherLikeStore.store(initTeacherLike)
        return true
    }
}