package com.sexysisters.tojserverv2.domain.school.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class SchoolNotFoundException : BaseException(SchoolErrorCode.SCHOOL_NOT_FOUND)