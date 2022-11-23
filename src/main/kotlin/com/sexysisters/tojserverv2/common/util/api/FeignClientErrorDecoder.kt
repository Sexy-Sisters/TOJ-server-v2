package com.sexysisters.tojserverv2.common.util.api

import com.sexysisters.tojserverv2.common.util.api.exception.OtherServerBadRequestException
import com.sexysisters.tojserverv2.common.util.api.exception.OtherServerExpiredTokenException
import com.sexysisters.tojserverv2.common.util.api.exception.OtherServerForbiddenException
import com.sexysisters.tojserverv2.common.util.api.exception.OtherServerUnauthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response): Exception {

        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw OtherServerUnauthorizedException()
                403 -> throw OtherServerForbiddenException()
                419 -> throw OtherServerExpiredTokenException()
                else -> throw OtherServerBadRequestException()
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}