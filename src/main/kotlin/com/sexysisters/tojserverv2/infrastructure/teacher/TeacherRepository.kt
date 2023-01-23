package com.sexysisters.tojserverv2.infrastructure.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TeacherRepository : JpaRepository<Teacher, UUID> {
    fun existsByNameValue(name: String): Boolean
    fun existsByNicknameValue(nickname: String): Boolean
}