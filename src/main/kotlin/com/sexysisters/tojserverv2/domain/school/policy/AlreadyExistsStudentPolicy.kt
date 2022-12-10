package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.StudentReader
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    한 학교에 같은 학년, 반, 번호는 존재할 수 없다.
 */
@Order(20)
@Component
class AlreadyExistsStudentPolicy(
    private val studentReader: StudentReader,
) : SchoolPolicy {

    override fun check(school: School, student: Student) {
        val grade = student.grade
        val classroom = student.classroom
        val number = student.number
        val hasStudent = studentReader.checkAlreadyExists(
            school,
            grade,
            classroom,
            number,
        )
        if (hasStudent) throw SchoolException.StudentAlreadyExists()
    }
}