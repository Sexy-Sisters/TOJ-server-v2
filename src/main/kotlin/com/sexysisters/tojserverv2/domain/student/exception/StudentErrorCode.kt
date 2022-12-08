package com.sexysisters.tojserverv2.domain.student.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class StudentErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    NOT_BELONG("You don't belong to the school"),
    STUDENT_NOT_FOUND("Student does not exist"),
    ALREADY_CREATED("You've already completed the student information form"),
    STUDENT_NOT_VALID("Student domain is not valid"),
}