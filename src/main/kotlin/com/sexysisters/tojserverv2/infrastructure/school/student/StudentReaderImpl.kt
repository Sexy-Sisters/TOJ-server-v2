package com.sexysisters.tojserverv2.infrastructure.school.student

import com.sexysisters.tojserverv2.domain.school.design.StudentReader
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.school.student.Student
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StudentReaderImpl(
    private val studentRepository: StudentRepository,
    private val userReader: UserReader,
) : StudentReader {

    override fun getStudent(id: Long): Student {
        return studentRepository.findByIdOrNull(id)
            ?: throw SchoolException.StudentNotFound()
    }

    override fun getCurrentStudent(): Student {
        return userReader.getCurrentUser().student
            ?: throw SchoolException.StudentNotFound()
    }
}