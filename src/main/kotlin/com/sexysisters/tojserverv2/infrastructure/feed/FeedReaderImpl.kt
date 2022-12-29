package com.sexysisters.tojserverv2.infrastructure.feed

import com.sexysisters.tojserverv2.domain.feed.FeedReader
import com.sexysisters.tojserverv2.domain.feed.exception.FeedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class FeedReaderImpl(
    private val feedRepository: FeedRepository,
) : FeedReader {
    override fun getFeed(id: Long) =
        feedRepository.findByIdOrNull(id)
            ?: throw FeedException.NotFound()
}