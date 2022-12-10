package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.infrastructure.student.StudentRepository
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    이미 존재하는 인적 사항인지 확인
 */
@Order(value = 20)
@Component
class AlreadyExistsStudentPolicy(
    private val studentRepository: StudentRepository,
) : SchoolPolicy {

    override fun check(student: Student, school: School) {
        val hasStudent = studentRepository.checkAlreadyExists(
            school = school,
            grade = student.grade,
            classroom = student.classroom,
            number = student.number,
        )
        if (hasStudent) {
            throw SchoolException.StudentAlreadyExists()
        }
    }
}