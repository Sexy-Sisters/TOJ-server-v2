package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import java.util.*

interface TeacherCommentReader {
    fun getComment(commentId: UUID): Comment
    fun getComments(): List<Comment>
}