package com.sexysisters.tojserverv2.common.response

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

class CommonResponse<T : Any?>(
    val result: Result,
    val data: T,
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

        fun fail(message: String, errorCode: String): CommonResponse<Unit> {
            return CommonResponse(
                data = Unit,
                result = Result.FAIL,
                message = message,
                errorCode = errorCode,
            )
        }

        fun fail(errorCode: ErrorCode): CommonResponse<Unit> {
            return CommonResponse(
                data = Unit,
                result = Result.FAIL,
                message = errorCode.errorMsg,
                errorCode = errorCode.name,
            )
        }
    }

    @Throws(JsonProcessingException::class)
    fun convertToJson(): String =
        ObjectMapper().writeValueAsString(this)

    enum class Result {
        SUCCESS, FAIL
    }
}