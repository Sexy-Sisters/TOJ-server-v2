package com.sexysisters.tojserverv2.interfaces.teacher.dto

class TeacherCommentRequest {
    data class Main(
        val content: String,
        val anonymous: Boolean
    )
}