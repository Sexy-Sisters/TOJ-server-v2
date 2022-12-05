package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class SchoolExcpetion {
    class AlreadyApply : BaseException(SchoolErrorCode.ALREADY_APPLIED)
    class SchoolNotFound : BaseException(SchoolErrorCode.SCHOOL_NOT_FOUND)
    class NotBelong : BaseException(SchoolErrorCode.NOT_BELONG)
}