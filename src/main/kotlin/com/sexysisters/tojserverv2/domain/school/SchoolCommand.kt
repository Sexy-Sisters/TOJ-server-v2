package com.sexysisters.tojserverv2.domain.school

class SchoolCommand {

    data class Search(
        val name: String,
        val belong: String,
    )

    data class CreateStudent(
        val age: Int,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}