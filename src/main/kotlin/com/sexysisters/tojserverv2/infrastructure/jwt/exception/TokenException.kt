package com.sexysisters.tojserverv2.infrastructure.jwt.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class TokenException {
    class AlreadyLogout : BaseException(JwtErrorCode.ALREADY_LOGOUT)
    class Expired : BaseException(JwtErrorCode.EXPIRED_TOKEN)
    class Invalid : BaseException(JwtErrorCode.INVALID_TOKEN)
}