package com.sexysisters.tojserverv2.domain.approve.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class ApproveException {
    class DifferentSchool : BaseException(ApproveErrorCode.DIFFERENT_SCHOOL)
    class AlreadyJoined : BaseException(ApproveErrorCode.ALREADY_COMPLETED)
}