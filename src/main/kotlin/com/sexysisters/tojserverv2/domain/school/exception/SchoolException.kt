package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class SchoolException {
    class SchoolNotFound : BaseException(SchoolErrorCode.SCHOOL_NOT_FOUND)
    class SchoolNotValid : BaseException(SchoolErrorCode.SCHOOL_NOT_VALID)
    class StudentAlreadyExists : BaseException(SchoolErrorCode.STUDENT_ALREADY_EXISTS)
    class StudentInfoOutOfRange : BaseException(SchoolErrorCode.STUDENT_INFO_OUT_OF_RANGE)
}