package com.sexysisters.tojserverv2.domain.feed.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class FeedErrorCode(
    override val errorMsg: String
) : ErrorProperty {
    NOT_FOUND("The feed does not exist"),
    WRITER_MISMATCH("The writer mismatch")
}