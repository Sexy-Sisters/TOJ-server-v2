package com.sexysisters.tojserverv2.domain.feed

import java.util.*

class FeedCommand {
    class Create(
        val content: String,
        val images: List<String>,
    )

    class Update(
        val id: UUID,
        val content: String,
    )
}