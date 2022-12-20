package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.TeacherReader
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import org.springframework.stereotype.Component

@Component
class TeacherReaderImpl(
    private val teacherCustomRepository: TeacherCustomRepository,
) : TeacherReader {
    override fun search(schoolCode: String): List<Teacher> {
        return teacherCustomRepository.search(schoolCode)
    }
}