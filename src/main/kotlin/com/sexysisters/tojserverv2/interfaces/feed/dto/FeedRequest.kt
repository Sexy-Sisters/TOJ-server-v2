package com.sexysisters.tojserverv2.interfaces.feed.dto

import javax.validation.constraints.NotNull

class FeedRequest {
    data class Create(
        @field:NotNull
        val content: String,
        val images: List<String>,
    )
}