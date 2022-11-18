package com.sexysisters.tojserverv2.common.exception

import com.sexysisters.tojserverv2.common.response.ErrorCode

open class BaseException : RuntimeException {

    var errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.errorMsg) {
        this.errorCode = errorCode
    }

    constructor(
        message: String,
        errorCode: ErrorCode,
    ) : super(message) {
        this.errorCode = errorCode
    }

    constructor(
        message: String,
        errorCode: ErrorCode,
        cause: Throwable,
    ) : super(message, cause) {
        this.errorCode = errorCode
    }
}