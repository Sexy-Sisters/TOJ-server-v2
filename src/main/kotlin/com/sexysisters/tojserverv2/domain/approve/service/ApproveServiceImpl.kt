package com.sexysisters.tojserverv2.domain.approve.service

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.approve.ApproveCommand
import com.sexysisters.tojserverv2.domain.approve.ApproveStore
import com.sexysisters.tojserverv2.domain.approve.policy.ApprovePolicy
import com.sexysisters.tojserverv2.domain.student.StudentReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApproveServiceImpl(
    private val approveStore: ApproveStore,
    private val approvePolicy: List<ApprovePolicy>,
    private val studentReader: StudentReader,
) : ApproveService {

    @Transactional
    override fun approve(command: ApproveCommand.Approve) {
        val acceptor = studentReader.getCurrentStudent()
        val applicant = studentReader.getStudent(command.applicantId)
        approvePolicy.forEach { it.check(applicant, acceptor) }
        val initApprove = Approve.createApprove(
            applicant = applicant,
            acceptor = acceptor,
        )
        approveStore.store(initApprove)
    }
}