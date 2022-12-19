package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacher.TeacherStore
import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import org.springframework.stereotype.Component

@Component
class TeacherStoreImpl(
    private val teacherRepository: TeacherRepository,
) : TeacherStore {
    override fun store(teacher: Teacher): Teacher {
        validate(teacher)
        return teacherRepository.save(teacher)
    }

    private fun validate(teacher: Teacher) {
        checkDuplicateName(teacher)
        checkDuplicateNickname(teacher)
    }

    private fun checkDuplicateName(teacher: Teacher) {
        val isDuplicate = teacherRepository.existsByNameValue(teacher.name.value)
        if (isDuplicate) throw TeacherException.DuplicateTeacherName()
    }

    private fun checkDuplicateNickname(teacher: Teacher) {
        val isDuplicate = teacherRepository.existsByNicknameValue(teacher.nickname.value)
        if (isDuplicate) throw TeacherException.DuplicateTeacherNickname()
    }
}