package com.sexysisters.tojserverv2.domain.feed.service

import com.sexysisters.tojserverv2.domain.feed.FeedCommand
import com.sexysisters.tojserverv2.domain.feed.FeedStore
import com.sexysisters.tojserverv2.domain.feed.domain.Content
import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import com.sexysisters.tojserverv2.domain.feed.domain.Image
import com.sexysisters.tojserverv2.domain.student.StudentReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FeedServiceImpl(
    private val feedStore: FeedStore,
    private val studentReader: StudentReader,
) : FeedService {

    @Transactional
    override fun createFeed(command: FeedCommand.Create): Long {
        val student = studentReader.getCurrentStudent()
        val feed = Feed(
            writer = student,
            content = Content(command.content),
            images = command.images.map { Image(it) }
        )
        student.feeds.add(feed)
        return feedStore.store(feed).id
    }
}