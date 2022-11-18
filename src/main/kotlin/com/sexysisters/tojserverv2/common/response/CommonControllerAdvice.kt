package com.sexysisters.tojserverv2.common.response

import com.sexysisters.tojserverv2.common.exception.BaseException
import org.apache.catalina.connector.ClientAbortException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.constraints.Null

@ControllerAdvice
class CommonControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(e: Exception): CommonResponse<Null> {
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException::class)
    fun onBaseException(e: BaseException): CommonResponse<Null> {
        return CommonResponse.fail(e.message!!, e.errorCode.errorMsg)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ClientAbortException::class)
    fun skipException(e: Exception): CommonResponse<Null> {
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<Null> {
        val bindingResult: BindingResult = e.bindingResult
        val fe: FieldError? = bindingResult.fieldError

        if (fe != null) {
            val message: String =
                "Request Error ${fe.field} = ${fe.rejectedValue} + (${fe.defaultMessage})"
            return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name)
        } else {
            return CommonResponse.fail(
                ErrorCode.COMMON_INVALID_PARAMETER.errorMsg,
                ErrorCode.COMMON_INVALID_PARAMETER.name
            )
        }
    }
}