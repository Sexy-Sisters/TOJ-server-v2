package com.sexysisters.tojserverv2.common.util.api.oauth.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class OtherServerException {
    class ExpiredToken : BaseException(OAuthErrorCode.OTHER_SERVER_EXPIRED_TOKEN)
    class BadRequest : BaseException(OAuthErrorCode.OTHER_SERVER_BAD_REQUEST)
    class Forbidden : BaseException(OAuthErrorCode.OTHER_SERVER_FORBIDDEN)
    class Unauthorized : BaseException(OAuthErrorCode.OTHER_SERVER_UNAUTHORIZED)
}