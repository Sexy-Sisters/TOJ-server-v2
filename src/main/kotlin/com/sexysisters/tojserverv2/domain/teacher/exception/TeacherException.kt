package com.sexysisters.tojserverv2.domain.teacher.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class TeacherException {
    class TeacherNotValid : BaseException(TeacherErrorCode.TEACHER_NOT_VALID)
    class DuplicateTeacherName : BaseException(TeacherErrorCode.DUPLICATE_NAME)
    class DuplicateTeacherNickname : BaseException(TeacherErrorCode.DUPLICATE_NICKNAME)
    class TeacherNotFound : BaseException(TeacherErrorCode.TEACHER_NOT_FOUND)
}