package com.sexysisters.tojserverv2.infrastructure.school.student

import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
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
            ?: throw StudentException.StudentNotFound()
    }

    override fun getCurrentStudent(): Student {
        return userReader.getCurrentUser().student
            ?: throw StudentException.StudentNotFound()
    }
}