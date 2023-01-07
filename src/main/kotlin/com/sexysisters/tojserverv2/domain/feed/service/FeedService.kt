package com.sexysisters.tojserverv2.domain.feed.service

import com.sexysisters.tojserverv2.domain.feed.FeedCommand
import java.util.UUID

interface FeedService {
    fun createFeed(command: FeedCommand.Create): UUID
    fun updateFeed(command: FeedCommand.Update)
}