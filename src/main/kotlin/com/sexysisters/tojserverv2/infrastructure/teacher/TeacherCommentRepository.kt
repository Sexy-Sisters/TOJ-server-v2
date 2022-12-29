package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherCommentRepository : JpaRepository<Comment, Long>