package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class SchoolErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    SCHOOL_NOT_FOUND("School does not exist"),
    SCHOOL_NOT_VALID("School domain is not valid"),
    STUDENT_ALREADY_EXISTS("Student already exists"),
    ALREADY_JOINED("You've already joined the school"),
}