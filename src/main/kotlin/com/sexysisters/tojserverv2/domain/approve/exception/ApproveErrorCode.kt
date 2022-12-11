package com.sexysisters.tojserverv2.domain.approve.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class ApproveErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    DIFFERENT_SCHOOL("Your school and the applicant's school are different"),
    ALREADY_COMPLETED("The applicant is already joined"),
    APPROVE_NOT_FOUND("You've never approve it"),
}