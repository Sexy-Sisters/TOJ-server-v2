package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.ApproveReader
import com.sexysisters.tojserverv2.domain.approve.exception.ApproveException
import com.sexysisters.tojserverv2.domain.student.Student
import org.springframework.stereotype.Component

@Component
class ApproveReaderImpl(
    private val approveRepository: ApproveRepository,
) : ApproveReader {

    override fun getApprove(applicant: Student, acceptor: Student) =
        approveRepository.findByApplicantAndAcceptor(applicant, acceptor)
            ?: throw ApproveException.ApproveNotFound()
}