package com.sexysisters.tojserverv2.domain.feed

import com.sexysisters.tojserverv2.domain.feed.domain.Feed

interface FeedReader {
    fun getFeed(id: String): Feed
}