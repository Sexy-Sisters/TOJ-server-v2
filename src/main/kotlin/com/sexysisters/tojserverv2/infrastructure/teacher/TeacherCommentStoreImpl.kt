package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommentStore
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import org.springframework.stereotype.Component

@Component
class TeacherCommentStoreImpl(
    private val teacherCommentRepository: TeacherCommentRepository,
) : TeacherCommentStore {

    override fun store(comment: Comment) = teacherCommentRepository.save(comment)
    override fun delete(comment: Comment) = teacherCommentRepository.delete(comment)
}