package com.sexysisters.tojserverv2.common.util.api.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class OAuthErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    OTHER_SERVER_BAD_REQUEST("Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED("Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN("Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN("Other server expired token"),
}