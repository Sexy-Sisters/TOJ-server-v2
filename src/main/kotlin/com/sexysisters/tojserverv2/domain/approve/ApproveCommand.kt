package com.sexysisters.tojserverv2.domain.approve

import java.util.*

class ApproveCommand {
    class Approve(
        val applicantId: UUID,
    )

    class Cancel(
        val applicantId: UUID,
    )
}