package com.sexysisters.tojserverv2.domain.wiki.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    소속된 학교에서만 위키를 수정할 수 있다.
*/

@Order(1)
@Component
class UpdateAuthority : WikiPolicy {
    override fun check(student: Student, school: School) {
        if (student.school!!.code != school.code) {
            throw StudentException.NotBelong()
        }
    }
}