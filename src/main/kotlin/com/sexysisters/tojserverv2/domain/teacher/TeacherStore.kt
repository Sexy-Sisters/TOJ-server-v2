package com.sexysisters.tojserverv2.domain.teacher

interface TeacherStore {
    fun store(teacher: Teacher): Long
}