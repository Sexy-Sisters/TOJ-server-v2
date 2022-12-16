package com.sexysisters.tojserverv2.domain.teacher.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class TeacherErrorCode(
    override val errorMsg: String
) : ErrorProperty {
    TEACHER_NOT_VALID("Teacher domain is not valid")
}