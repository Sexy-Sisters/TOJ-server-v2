package com.sexysisters.tojserverv2.interfaces.wiki.dto

class WikiResponse {

    data class Main(
        val id: Long,
        val name: String,
        val html: String,
        val markdown: String,
        val views: Int,
        val updatedAt: String
        val wallpaper: String,
    )
}