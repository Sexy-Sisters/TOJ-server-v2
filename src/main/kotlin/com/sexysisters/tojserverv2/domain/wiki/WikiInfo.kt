package com.sexysisters.tojserverv2.domain.wiki

class WikiInfo {

    class Main(
        val id: String,
        val name: String,
        val html: String,
        val markdown: String,
        val views: Int,
        val updatedAt: String,
        val wallpaper: String,
    )
}