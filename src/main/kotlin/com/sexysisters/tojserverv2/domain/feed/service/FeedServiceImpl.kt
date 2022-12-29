package com.sexysisters.tojserverv2.domain.feed.service

import com.sexysisters.tojserverv2.domain.feed.FeedCommand
import com.sexysisters.tojserverv2.domain.feed.FeedReader
import com.sexysisters.tojserverv2.domain.feed.FeedStore
import com.sexysisters.tojserverv2.domain.feed.domain.Content
import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import com.sexysisters.tojserverv2.domain.feed.domain.Image
import com.sexysisters.tojserverv2.domain.feed.exception.FeedException
import com.sexysisters.tojserverv2.domain.student.StudentReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FeedServiceImpl(
    private val feedReader: FeedReader,
    private val feedStore: FeedStore,
    private val studentReader: StudentReader,
) : FeedService {

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

    override fun updateFeed(command: FeedCommand.Update) {
        val feed = feedReader.getFeed(command.id)
        val student = studentReader.getCurrentStudent()
        if (feed.writer != student) throw FeedException.WriterMismatch()
        val updatedContent = Content(command.content)
        feed.update(updatedContent)
    }
}