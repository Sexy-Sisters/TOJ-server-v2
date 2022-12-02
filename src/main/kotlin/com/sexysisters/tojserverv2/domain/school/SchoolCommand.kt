package com.sexysisters.tojserverv2.domain.school

class SchoolCommand {

    data class SearchRequest(
        val name: String,
        val belong: String,
    )
}