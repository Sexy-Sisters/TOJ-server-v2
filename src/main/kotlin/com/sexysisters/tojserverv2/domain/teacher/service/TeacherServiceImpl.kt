package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.teacher.*
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.hasStudent
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeacherServiceImpl(
    private val userReader: UserReader,
    private val teacherStore: TeacherStore,
    private val teacherReader: TeacherReader,
    private val teacherEntityMapper: TeacherEntityMapper,
    private val teacherMapper: TeacherMapper,
) : TeacherService {

    @Transactional
    override fun createTeacher(command: TeacherCommand.Create) {
        val user = userReader.getCurrentUser()
        if (!user.hasStudent()) throw StudentException.StudentNotFound()
        val teacher = teacherEntityMapper.of(command)
        teacherStore.store(teacher)
    }

    override fun getTeachers(schoolCode: String): List<TeacherResponse.Search> {
        val teachers = teacherReader.search(schoolCode)
        return teachers.map { teacherMapper.of(it) }
    }
}