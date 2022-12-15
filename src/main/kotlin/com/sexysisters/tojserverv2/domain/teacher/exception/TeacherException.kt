package com.sexysisters.tojserverv2.domain.teacher.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class TeacherException {
    class TeacherNotValid : BaseException(TeacherErrorCode.TEACHER_NOT_VALID)
}