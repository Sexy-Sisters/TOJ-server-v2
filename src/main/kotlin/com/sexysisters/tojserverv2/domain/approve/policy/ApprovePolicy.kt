package com.sexysisters.tojserverv2.domain.approve.policy

import com.sexysisters.tojserverv2.domain.student.domain.Student

interface ApprovePolicy {
    fun check(applicant: Student, acceptor: Student)
}