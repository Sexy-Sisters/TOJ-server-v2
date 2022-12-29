package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment

interface TeacherCommentReader {
    fun getComment(commentId: Long) : Comment
    fun getComments() : List<Comment>
}