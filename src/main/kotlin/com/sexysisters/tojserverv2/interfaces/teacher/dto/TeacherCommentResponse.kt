package com.sexysisters.tojserverv2.interfaces.teacher.dto

import java.time.LocalDateTime

class TeacherCommentResponse {

    data class Main(
        val profileImg: String,
        val nickname: String,
        val content: String,
        val createdDate: LocalDateTime
    )
}