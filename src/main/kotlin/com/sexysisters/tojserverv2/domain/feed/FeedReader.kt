package com.sexysisters.tojserverv2.domain.feed

import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import java.util.*

interface FeedReader {
    fun getFeed(id: UUID): Feed
}