package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.engaged
import com.sexysisters.tojserverv2.domain.student.waiting
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    참가 중인 학생이 5명 이하면 바로 참가
*/
@Order(30)
@Component
class DirectJoinPolicy : SchoolPolicy {

    override fun check(student: Student, school: School) {
        val studentCount = school.studentList.size
        if (studentCount <= 5) {
            student.engaged()
        } else {
            student.waiting()
        }
    }
}