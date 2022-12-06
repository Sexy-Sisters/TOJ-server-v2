package com.sexysisters.tojserverv2.domain.school

class SchoolCommand {

    data class Search(
        val name: String,
        val belong: String,
    )
}