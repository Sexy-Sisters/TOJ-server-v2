package com.sexysisters.tojserverv2.domain.approve.policy

import com.sexysisters.tojserverv2.domain.approve.exception.ApproveException
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    신청한 학생과 수락하는 학생의 학교가 같아야 한다.
 */
@Order(20)
@Component
class SameSchoolPolicy : ApprovePolicy {

    override fun check(applicant: Student, acceptor: Student) {
        val applicantSchool = applicant.school
        val acceptorSchool = acceptor.school
        val isSame = acceptorSchool.code == applicantSchool.code
        if (!isSame) {
            throw ApproveException.DifferentSchool()
        }
    }
}