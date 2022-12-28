package com.sexysisters.tojserverv2.domain.feed.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class FeedException {
    class NotFound : BaseException(FeedErrorCode.NOT_FOUND)
    class WriterMismatch : BaseException(FeedErrorCode.WRITER_MISMATCH)
}