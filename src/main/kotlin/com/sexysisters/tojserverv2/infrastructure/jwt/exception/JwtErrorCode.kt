package com.sexysisters.tojserverv2.infrastructure.jwt.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class JwtErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    EXPIRED_TOKEN("Token was expired"),
    INVALID_TOKEN("This token is not valid"),
    ALREADY_LOGOUT("Already logout"),
}