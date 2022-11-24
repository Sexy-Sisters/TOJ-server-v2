package com.sexysisters.tojserverv2.common.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

open class BaseException : RuntimeException {

    var errorCode: ErrorProperty

    constructor(errorCode: ErrorProperty) : super(errorCode.errorMsg) {
        this.errorCode = errorCode
    }

    constructor(
        message: String,
        errorCode: ErrorProperty,
    ) : super(message) {
        this.errorCode = errorCode
    }

    constructor(
        message: String,
        errorCode: ErrorProperty,
        cause: Throwable,
    ) : super(message, cause) {
        this.errorCode = errorCode
    }
}