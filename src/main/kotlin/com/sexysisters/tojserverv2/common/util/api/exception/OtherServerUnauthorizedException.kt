package com.sexysisters.tojserverv2.common.util.api.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class OtherServerUnauthorizedException : BaseException(OAuthErrorCode.OTHER_SERVER_UNAUTHORIZED)