package com.sexysisters.tojserverv2.interfaces.wiki.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class WikiRequest {

    class Update(
        @field: NotNull
        val id: Long,
        @field: NotEmpty
        val content: String,
    )
}
