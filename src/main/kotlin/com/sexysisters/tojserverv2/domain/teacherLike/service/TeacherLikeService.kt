package com.sexysisters.tojserverv2.domain.teacherLike.service

interface TeacherLikeService {
    fun like(teacherId: Long): Boolean
}