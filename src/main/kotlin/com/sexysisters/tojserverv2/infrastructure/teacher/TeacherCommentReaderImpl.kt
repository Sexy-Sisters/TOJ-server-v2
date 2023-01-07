package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentReader
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherCommentException
import org.springframework.stereotype.Component
import java.util.*

@Component
class TeacherCommentReaderImpl(
    private val teacherCommentRepository: TeacherCommentRepository,
) : TeacherCommentReader {

    override fun getComment(commentId: UUID): Comment {
        return teacherCommentRepository.findById(commentId)
            ?: throw TeacherCommentException.TeacherCommentNotFound()
    }

    override fun getComments(): List<Comment> {
        return teacherCommentRepository.findAll()
    }
}