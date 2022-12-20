package com.sexysisters.tojserverv2.interfaces.teacher.dto

class TeacherResponse {
    data class Search(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String,
    )

    data class Get(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String,
        //TODO : add comments
    )
}