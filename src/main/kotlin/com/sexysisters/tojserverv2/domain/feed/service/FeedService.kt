package com.sexysisters.tojserverv2.domain.feed.service

import com.sexysisters.tojserverv2.domain.feed.FeedCommand

interface FeedService {

    fun createFeed(command: FeedCommand.Create): Long
}