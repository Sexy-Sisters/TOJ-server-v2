package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.student.StudentStore
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import org.springframework.stereotype.Component

@Component
class StudentStoreImpl(
    private val studentRepository: StudentRepository,
) : StudentStore {

    override fun store(student: Student): Student {
        validate(student)
        return studentRepository.save(student)
    }

    private fun validate(student: Student) {
        val hasStudent = studentRepository.checkAlreadyExists(
            school = student.school,
            grade = student.gradeValue(),
            classroom = student.classroomValue(),
            number = student.numberValue(),
        )
        if (hasStudent) {
            throw StudentException.DuplicatedStudent()
        }
    }
}