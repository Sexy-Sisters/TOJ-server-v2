package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.student.domain.Status
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.user.UserReader
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StudentReaderImpl(
    private val studentRepository: StudentRepository,
    private val userReader: UserReader,
) : StudentReader {

    override fun getStudent(id: Long) =
        studentRepository.findByIdOrNull(id)
            ?: throw StudentException.StudentNotFound()

    override fun getCurrentStudent() =
        userReader.getCurrentUser().student
            ?: throw StudentException.StudentNotFound()

    override fun checkAlreadyExists(
        school: School,
        grade: Int,
        classroom: Int,
        number: Int,
    ) = studentRepository.checkAlreadyExists(
        school = school,
        grade = grade,
        classroom = classroom,
        number = number,
    )

    override fun getStudentList(school: School, status: Status) = studentRepository.findAllBySchoolAndStatus(school, status)
}