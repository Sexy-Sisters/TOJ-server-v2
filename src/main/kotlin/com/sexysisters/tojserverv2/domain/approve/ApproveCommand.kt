package com.sexysisters.tojserverv2.domain.approve

class ApproveCommand {
    class Approve(
        val applicantId: String,
    )

    class Cancel(
        val applicantId: String,
    )
}