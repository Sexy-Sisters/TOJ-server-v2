package com.sexysisters.tojserverv2.infrastructure.feed

import com.sexysisters.tojserverv2.domain.feed.FeedReader
import com.sexysisters.tojserverv2.domain.feed.exception.FeedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class FeedReaderImpl(
    private val feedRepository: FeedRepository,
) : FeedReader {
    override fun getFeed(id: UUID) =
        feedRepository.findByIdOrNull(id)
            ?: throw FeedException.NotFound()
}