package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentCommand
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse

interface TeacherCommentService {
    fun create(teacherId: Long, command: TeacherCommentCommand.Main)
    fun create(teacherId: Long, commentId: Long, command: TeacherCommentCommand.Main)
    fun update(commentId: Long, command: TeacherCommentCommand.Main)
    fun delete(commentId: Long)
    fun getComments() : List<TeacherCommentResponse.Main>
}