package com.sexysisters.tojserverv2.domain.wiki

class WikiCommand {

    class Update(
        val id: String,
        val html: String,
        val markdown: String,
    )
}