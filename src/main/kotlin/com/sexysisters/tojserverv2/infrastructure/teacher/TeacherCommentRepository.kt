package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TeacherCommentRepository : JpaRepository<Comment, String> {
    fun findById(id: UUID): Comment?
}