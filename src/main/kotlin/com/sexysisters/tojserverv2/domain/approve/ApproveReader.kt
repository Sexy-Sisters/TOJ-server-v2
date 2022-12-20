package com.sexysisters.tojserverv2.domain.approve

import com.sexysisters.tojserverv2.domain.student.domain.Student

interface ApproveReader {
    fun getApprove(applicant: Student, acceptor: Student): Approve
}