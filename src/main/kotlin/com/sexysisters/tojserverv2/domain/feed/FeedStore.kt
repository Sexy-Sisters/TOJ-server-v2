package com.sexysisters.tojserverv2.domain.feed

import com.sexysisters.tojserverv2.domain.feed.domain.Feed

interface FeedStore {
    fun store(feed: Feed): Feed
}