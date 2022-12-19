package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.school.type.Division
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    <학생 정보 범위 체크>
    학년(초: 1~6/중,고: 1~3),
    반(1~20),
    번호(1~100)
 */
@Order(10)
@Component
class InfoRangePolicy : SchoolPolicy {

    override fun check(student: Student, school: School) {
        val schoolDivision = school.division
        val grade = student.grade
        val classroom = student.classroom
        val number = student.number

        val isValidGrade = when (schoolDivision) {
            Division.ELEMENTARY -> grade.value in 1..6
            else -> grade.value in 1..3
        }
        val isValidClassroom = classroom.value in 1..20
        val isValidNumber = number.value in 1..100

        val isValidInfo = isValidGrade && isValidClassroom && isValidNumber
        if (!isValidInfo) {
            throw SchoolException.StudentInfoOutOfRange()
        }
    }
}