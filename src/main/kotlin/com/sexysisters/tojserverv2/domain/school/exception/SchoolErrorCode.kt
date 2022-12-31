package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class SchoolErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    SCHOOL_NOT_FOUND("The school does not exist"),
    SCHOOL_NOT_VALID("School domain is not valid"),
    STUDENT_INFO_OUT_OF_RANGE("Student information is out of range"),
}