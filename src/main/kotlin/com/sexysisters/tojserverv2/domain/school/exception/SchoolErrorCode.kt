package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class SchoolErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    SCHOOL_NOT_FOUND("School does not exist"),
    SCHOOL_EMPTY_PROPERTIES("Creating School is failed"),
}