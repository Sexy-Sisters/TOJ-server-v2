package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.domain.student.exception.StudentErrorCode

class SchoolException {
    class SchoolNotFound : BaseException(SchoolErrorCode.SCHOOL_NOT_FOUND)
    class AlreadyApplied : BaseException(StudentErrorCode.ALREADY_APPLIED)
    class AlreadyJoined : BaseException(StudentErrorCode.ALREADY_JOINED)
}