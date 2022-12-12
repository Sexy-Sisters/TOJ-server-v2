package com.sexysisters.tojserverv2.common.filter

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.common.response.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtExceptionFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BaseException) {
            setErrorResponse(response, e)
        }
    }

    private fun setErrorResponse(response: HttpServletResponse, e: BaseException) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json; charset=UTF-8"
        val commonResponse = CommonResponse.fail(e.errorCode)
        response.writer.write(commonResponse.convertToJson())
    }
}