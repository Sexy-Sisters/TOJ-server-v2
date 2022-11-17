package com.sexysisters.tojserverv2.common.response

import javax.validation.constraints.Null
import com.sexysisters.tojserverv2.common.response.CommonResponse as CommonResponse


class CommonResponse<T: Any?> (
    val result: Result,
    val data: T? = null,
    val message: String? = null,
    val errorCode: String? = null,
) {

    companion object {

        fun <T> success(data: T, message: String?): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = message,
            )
        }

        fun <T> success(data: T): CommonResponse<T> {
            return success(data, null)
        }

        fun fail(message: String, errorCode: String): CommonResponse<Null> {
            return CommonResponse(
                result = Result.FAIL,
                message = message,
                errorCode = errorCode,
            )
        }

        fun fail(errorCode: ErrorCode): CommonResponse<Null> {
            return CommonResponse(
                result = Result.FAIL,
                message = errorCode.errorMsg,
                errorCode = errorCode.name,
            )
        }
    }

    enum class Result {
        SUCCESS, FAIL
    }
}



