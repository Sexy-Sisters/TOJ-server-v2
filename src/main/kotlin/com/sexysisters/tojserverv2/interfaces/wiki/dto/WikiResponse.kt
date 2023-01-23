package com.sexysisters.tojserverv2.interfaces.wiki.dto

class WikiResponse {

    data class Main(
        val id: String,
        val name: String,
        val html: String,
        val markdown: String,
        val views: Int,
        val wallpaper: String,
        val updatedAt: String,
    )
}