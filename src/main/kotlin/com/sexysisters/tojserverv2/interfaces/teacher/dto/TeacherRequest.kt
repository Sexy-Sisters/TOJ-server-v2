package com.sexysisters.tojserverv2.interfaces.teacher.dto

class TeacherRequest {

    data class Create(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String
    )
}