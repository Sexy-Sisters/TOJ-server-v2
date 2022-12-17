package com.sexysisters.tojserverv2.interfaces.wiki.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class WikiRequest {

    class Update(
        @field: NotNull
        val id: Long,
        @field: NotBlank
        val html: String,
        @field: NotBlank
        val markdown: String,
    )
}