package com.sexysisters.tojserverv2.domain.wiki

class WikiInfo {

    class Main(
        val id: Long,
        val name: String,
        val html: String,
        val markdown: String,
        val views: Int,
    )
}