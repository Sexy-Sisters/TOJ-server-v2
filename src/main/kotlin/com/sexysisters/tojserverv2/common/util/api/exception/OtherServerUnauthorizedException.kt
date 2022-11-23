package com.sexysisters.tojserverv2.common.util.api.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.common.response.ErrorCode

class OtherServerUnauthorizedException : BaseException(ErrorCode.OTHER_SERVER_UNAUTHORIZED)