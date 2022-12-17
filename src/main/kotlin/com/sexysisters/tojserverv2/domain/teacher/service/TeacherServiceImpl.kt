package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.teacher.*
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.hasStudent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeacherServiceImpl(
    private val userReader: UserReader,
    private val teacherStore: TeacherStore,
    private val teacherEntityMapper: TeacherEntityMapper,
) : TeacherService {

    @Transactional
    override fun createTeacher(command: TeacherCommand.Create) {
        val user = userReader.getCurrentUser()
        if (!user.hasStudent()) throw StudentException.StudentNotFound()
        val teacher = teacherEntityMapper.of(command)
        teacherStore.store(teacher)
    }
}