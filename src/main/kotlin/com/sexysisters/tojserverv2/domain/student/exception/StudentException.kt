package com.sexysisters.tojserverv2.domain.student.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class StudentException {
    class StudentNotFound : BaseException(StudentErrorCode.STUDENT_NOT_FOUND)
    class NotBelong : BaseException(StudentErrorCode.NOT_BELONG)
    class AlreadyCreated : BaseException(StudentErrorCode.ALREADY_CREATED)
    class StudentNotValid : BaseException(StudentErrorCode.STUDENT_NOT_VALID)
    class DifferentStudent : BaseException(StudentErrorCode.DIFFERENT_STUDENT)
}