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

        fun <T> success(data: T, message: String?) =
            CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = message,
            )

        fun <T> success(data: T) = success(data, null)

        fun fail(message: String, errorCode: String) =
            CommonResponse(
                data = Unit,
                result = Result.FAIL,
                message = message,
                errorCode = errorCode,
            )

        fun fail(errorCode: ErrorProperty) =
            CommonResponse(
                data = Unit,
                result = Result.FAIL,
                message = errorCode.errorMsg,
                errorCode = errorCode.errorMsg,
            )
    }

    @Throws(JsonProcessingException::class)
    fun convertToJson(): String = ObjectMapper().writeValueAsString(this)

    enum class Result {
        SUCCESS, FAIL
    }
}