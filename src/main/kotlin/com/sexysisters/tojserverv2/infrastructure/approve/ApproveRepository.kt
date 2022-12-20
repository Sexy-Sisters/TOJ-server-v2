package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface ApproveRepository : JpaRepository<Approve, Long> {
    fun findByApplicantAndAcceptor(applicant: Student, acceptor: Student): Approve?
}