package com.sexysisters.tojserverv2.domain.approve

class ApproveCommand {
    class Approve(
        val applicantId: Long,
    )

    class Cancel(
        val applicantId: Long,
    )
}