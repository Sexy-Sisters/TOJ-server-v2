package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentCommand
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse
import java.util.UUID

interface TeacherCommentService {
    fun create(teacherId: UUID, command: TeacherCommentCommand.Main)
    fun create(teacherId: UUID, commentId: UUID, command: TeacherCommentCommand.Main)
    fun update(commentId: UUID, command: TeacherCommentCommand.Main)
    fun delete(commentId: UUID)
    fun getComments(): List<TeacherCommentResponse.Main>
}