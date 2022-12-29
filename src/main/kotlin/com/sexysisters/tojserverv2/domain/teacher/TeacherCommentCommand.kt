package com.sexysisters.tojserverv2.domain.teacher

class TeacherCommentCommand {
    data class Main(
        val content: String,
        val anonymous: Boolean
    )
}