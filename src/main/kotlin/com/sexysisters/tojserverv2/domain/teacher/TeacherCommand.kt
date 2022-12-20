package com.sexysisters.tojserverv2.domain.teacher

class TeacherCommand {

    data class Create(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String,
    )

    data class Update(
        val image: String,
        val name: String,
        val nickname: String,
        val bio: String,
    )
}