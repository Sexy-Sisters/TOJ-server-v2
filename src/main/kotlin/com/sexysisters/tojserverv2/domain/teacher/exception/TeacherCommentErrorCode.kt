package com.sexysisters.tojserverv2.domain.teacher.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class TeacherCommentErrorCode(
    override val errorMsg: String
) : ErrorProperty {
    TEACHER_COMMENT_NOT_VALID("Teacher comment is not valid"),
    TEACHER_COMMENT_NOT_FOUND("Teacher comment is not found"),
}