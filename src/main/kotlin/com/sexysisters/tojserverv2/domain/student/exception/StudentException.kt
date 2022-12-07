package com.sexysisters.tojserverv2.domain.student.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class StudentException {
    class StudentNotFound : BaseException(StudentErrorCode.STUDENT_NOT_FOUND)
    class AlreadyApplied : BaseException(StudentErrorCode.ALREADY_APPLIED)
    class AlreadyJoined : BaseException(StudentErrorCode.ALREADY_JOINED)
    class NotBelong : BaseException(StudentErrorCode.NOT_BELONG)
    class AlreadyCreated : BaseException(StudentErrorCode.ALREADY_CREATED)
}