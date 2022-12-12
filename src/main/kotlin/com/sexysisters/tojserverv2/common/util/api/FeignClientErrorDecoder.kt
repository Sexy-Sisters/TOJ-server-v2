package com.sexysisters.tojserverv2.common.util.api

import com.sexysisters.tojserverv2.common.util.api.oauth.exception.OtherServerException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response): Exception {

        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw OtherServerException.Unauthorized()
                403 -> throw OtherServerException.Forbidden()
                419 -> throw OtherServerException.ExpiredToken()
                else -> throw OtherServerException.BadRequest()
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}