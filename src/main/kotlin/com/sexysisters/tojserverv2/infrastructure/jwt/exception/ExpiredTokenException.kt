package com.sexysisters.tojserverv2.infrastructure.jwt.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.common.response.ErrorCode

class ExpiredTokenException : BaseException(ErrorCode.EXPIRED_TOKEN)