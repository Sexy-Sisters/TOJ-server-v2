package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.domain.engaged
import com.sexysisters.tojserverv2.domain.student.domain.waiting
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    참가 중인 학생이 5명 이하면 바로 참가
*/
@Order(40)
@Component
class DirectJoinPolicy : SchoolPolicy {

    override fun check(student: Student, school: School) {
        val studentCount = school.students.size
        if (studentCount <= 5) {
            student.engaged()
        } else {
            student.waiting()
        }
    }
}