package com.sexysisters.tojserverv2.interfaces.wiki.dto

class WikiResponse {

    data class Main(
        val id: Long,
        val name: String,
        val content: String,
    )
}