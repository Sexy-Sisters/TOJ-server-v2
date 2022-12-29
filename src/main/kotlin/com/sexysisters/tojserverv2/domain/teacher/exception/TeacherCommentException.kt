package com.sexysisters.tojserverv2.domain.teacher.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class TeacherCommentException {
    class TeacherCommentNotValid : BaseException(TeacherCommentErrorCode.TEACHER_COMMENT_NOT_VALID)
    class TeacherCommentNotFound : BaseException(TeacherCommentErrorCode.TEACHER_COMMENT_NOT_VALID)
}