package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentReader
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherCommentException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TeacherCommentReaderImpl(
    private val teacherCommentRepository: TeacherCommentRepository,
) : TeacherCommentReader {

    override fun getComment(commentId: Long): Comment {
        return teacherCommentRepository.findByIdOrNull(commentId)
            ?: throw TeacherCommentException.TeacherCommentNotFound()
    }

    override fun getComments(): List<Comment> {
        return teacherCommentRepository.findAll()
    }
}