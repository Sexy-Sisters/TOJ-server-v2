package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.student.StudentStore
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.stereotype.Component

@Component
class StudentStoreImpl(
    private val studentRepository: StudentRepository,
) : StudentStore {

    override fun store(student: Student): Student {
        return studentRepository.save(student)
    }
}