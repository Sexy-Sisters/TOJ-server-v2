package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ApproveRepository : JpaRepository<Approve, String> {
    fun findByApplicantAndAcceptor(applicant: Student, acceptor: Student): Approve?
    fun existsById(id: UUID): Boolean
}