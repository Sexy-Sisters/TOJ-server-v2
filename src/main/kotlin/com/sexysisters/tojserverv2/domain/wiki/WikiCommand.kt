package com.sexysisters.tojserverv2.domain.wiki

class WikiCommand {

    class Update(
        val id: Long,
        val html: String,
        val markdown: String,
    )
}