package com.sexysisters.tojserverv2.domain.wiki

import java.util.*

class WikiCommand {

    class Update(
        val id: UUID,
        val html: String,
        val markdown: String,
    )
}