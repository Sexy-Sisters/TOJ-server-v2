package com.sexysisters.tojserverv2.interfaces.approve.dto

import javax.validation.constraints.NotBlank

class ApproveRequest {

    data class Approve (
        @field: NotBlank
        val applicantId: Long,
    )
}
