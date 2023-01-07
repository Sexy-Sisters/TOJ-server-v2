package com.sexysisters.tojserverv2.domain.approve

import com.sexysisters.tojserverv2.domain.BaseEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_approve")
class Approve(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "applicant_id")
    val applicant: Student,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "acceptor_id")
    val acceptor: Student,
) : BaseEntity() {

    companion object {
        fun createApprove(
            applicant: Student,
            acceptor: Student,
        ): Approve {
            val initApprove = Approve(
                applicant = applicant,
                acceptor = acceptor,
            )
            applicant.approves.add(initApprove)
            acceptor.acceptors.add(initApprove)
            return initApprove
        }
    }
}