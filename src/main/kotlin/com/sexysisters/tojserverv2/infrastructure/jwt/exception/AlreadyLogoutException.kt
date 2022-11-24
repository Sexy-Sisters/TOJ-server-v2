package com.sexysisters.tojserverv2.infrastructure.jwt.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class AlreadyLogoutException : BaseException(JwtErrorCode.ALREADY_LOGOUT)