package com.sexysisters.tojserverv2.common.util.api.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class OtherServerExpiredTokenException : BaseException(OAuthErrorCode.OTHER_SERVER_EXPIRED_TOKEN)