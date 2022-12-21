package com.sexysisters.tojserverv2.interfaces.teacher.dto

class TeacherResponse {
    data class Main(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String,
    )
}