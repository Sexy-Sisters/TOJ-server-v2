package com.sexysisters.tojserverv2.domain.approve.service

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.approve.ApproveCommand
import com.sexysisters.tojserverv2.domain.approve.ApproveReader
import com.sexysisters.tojserverv2.domain.approve.ApproveStore
import com.sexysisters.tojserverv2.domain.approve.policy.ApprovePolicy
import com.sexysisters.tojserverv2.domain.student.StudentReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApproveServiceImpl(
    private val approveStore: ApproveStore,
    private val approveReader: ApproveReader,
    private val approvePolicy: List<ApprovePolicy>,
    private val studentReader: StudentReader,
) : ApproveService {

    @Transactional
    override fun approve(command: ApproveCommand.Approve) {
        val applicant = studentReader.getStudent(command.applicantId)
        val acceptor = studentReader.getCurrentStudent()
        approvePolicy.forEach { it.check(applicant, acceptor) }
        val initApprove = Approve.createApprove(
            applicant = applicant,
            acceptor = acceptor,
        )
        approveStore.store(initApprove)
    }

    @Transactional
    override fun cancel(command: ApproveCommand.Cancel) {
        val applicant = studentReader.getStudent(command.applicantId)
        val acceptor = studentReader.getCurrentStudent()
        val approve = approveReader.getApprove(applicant, acceptor)
        approveStore.delete(approve)
    }
}