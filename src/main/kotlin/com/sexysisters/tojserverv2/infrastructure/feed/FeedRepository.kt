package com.sexysisters.tojserverv2.infrastructure.feed

import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FeedRepository : JpaRepository<Feed, UUID>