package com.sexysisters.tojserverv2.common.util.api.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.common.response.ErrorCode

class OtherServerBadRequestException : BaseException(OAuthErrorCode.OTHER_SERVER_BAD_REQUEST)