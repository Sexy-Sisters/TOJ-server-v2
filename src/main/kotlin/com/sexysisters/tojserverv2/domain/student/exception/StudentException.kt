package com.sexysisters.tojserverv2.domain.student.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class StudentException {
    class StudentNotFound : BaseException(StudentErrorCode.STUDENT_NOT_FOUND)
    class NotBeString : BaseException(StudentErrorCode.NOT_BEString)
    class AlreadyCreated : BaseException(StudentErrorCode.ALREADY_CREATED)
    class StudentNotValid : BaseException(StudentErrorCode.STUDENT_NOT_VALID)
    class DuplicatedStudent : BaseException(StudentErrorCode.DUPLICATED_STUDENT)
    class DifferentStudent : BaseException(StudentErrorCode.DIFFERENT_STUDENT)
}