package com.sexysisters.tojserverv2.domain.approve

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tbl_approve")
class Approve(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "applicant_id")
    val applicant: Student,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "acceptor_id")
    val acceptor: Student,
) : BaseTimeEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

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