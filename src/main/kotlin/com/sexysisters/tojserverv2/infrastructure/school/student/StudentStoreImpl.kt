package com.sexysisters.tojserverv2.infrastructure.school.student

import com.sexysisters.tojserverv2.domain.school.design.StudentStore
import com.sexysisters.tojserverv2.domain.school.student.Student
import org.springframework.stereotype.Component

@Component
class StudentStoreImpl(
    private val studentRepository: StudentRepository,
) : StudentStore {
    override fun store(student: Student): Long {
        return studentRepository.save(student).id
    }
}