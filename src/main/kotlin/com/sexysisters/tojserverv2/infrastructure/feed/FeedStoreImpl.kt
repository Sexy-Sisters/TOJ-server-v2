package com.sexysisters.tojserverv2.infrastructure.feed

import com.sexysisters.tojserverv2.domain.feed.FeedStore
import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import org.springframework.stereotype.Component

@Component
class FeedStoreImpl(
    private val feedRepository: FeedRepository,
) : FeedStore {

    override fun store(feed: Feed): Feed {
        return feedRepository.save(feed)
    }
}