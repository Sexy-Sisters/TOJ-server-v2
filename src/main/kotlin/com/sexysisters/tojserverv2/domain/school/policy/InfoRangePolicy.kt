package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.domain.Division
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
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
        val grade = student.getGradeValue()
        val classroom = student.getClassroomValue()
        val number = student.getNumberValue()

        val isValidGrade = when (schoolDivision) {
            Division.ELEMENTARY -> grade in 1..6
            else -> grade in 1..3
        }
        val isValidClassroom = classroom in 1..20
        val isValidNumber = number in 1..100

        val isValidInfo = isValidGrade && isValidClassroom && isValidNumber
        if (!isValidInfo) {
            throw SchoolException.StudentInfoOutOfRange()
        }
    }
}