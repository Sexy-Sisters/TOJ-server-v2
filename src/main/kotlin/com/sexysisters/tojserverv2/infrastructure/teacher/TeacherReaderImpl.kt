package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.teacher.TeacherReader
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TeacherReaderImpl(
    private val teacherCustomRepository: TeacherCustomRepository,
    private val teacherRepository: TeacherRepository,
) : TeacherReader {
    override fun search(schoolCode: String) = teacherCustomRepository.search(schoolCode)

    override fun getTeacher(id: Long) = teacherRepository.findByIdOrNull(id)
        ?: throw TeacherException.TeacherNotFound()

    override fun getTeacher(id: Long, school: School) = teacherCustomRepository.get(id, school.code.value)
            ?: throw TeacherException.TeacherNotFound()
}