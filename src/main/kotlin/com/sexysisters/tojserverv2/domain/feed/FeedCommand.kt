package com.sexysisters.tojserverv2.domain.feed

class FeedCommand {
    class Create(
        val content: String,
        val images: List<String>,
    )
}