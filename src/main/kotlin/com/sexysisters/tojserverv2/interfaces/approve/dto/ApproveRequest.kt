package com.sexysisters.tojserverv2.interfaces.approve.dto

import javax.validation.constraints.NotNull

class ApproveRequest {

    data class Approve(
        @field: NotNull
        val applicantId: Long,
    )

    data class Cancel(
        @field: NotNull
        val applicantId: Long,
    )
}