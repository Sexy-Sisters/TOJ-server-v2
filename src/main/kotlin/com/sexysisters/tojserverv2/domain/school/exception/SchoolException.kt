package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class SchoolException {
    class SchoolNotFound : BaseException(SchoolErrorCode.SCHOOL_NOT_FOUND)
    class NotBelong : BaseException(SchoolErrorCode.NOT_BELONG)
    class StudentNotFound : BaseException(SchoolErrorCode.STUDENT_NOT_FOUND)
    class AlreadyApplied : BaseException(SchoolErrorCode.ALREADY_APPLIED)
    class AlreadyJoined : BaseException(SchoolErrorCode.ALREADY_JOINED)
}