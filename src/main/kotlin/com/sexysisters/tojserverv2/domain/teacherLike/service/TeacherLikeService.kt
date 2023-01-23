package com.sexysisters.tojserverv2.domain.teacherLike.service

import java.util.*

interface TeacherLikeService {
    fun like(teacherId: UUID): Boolean
}