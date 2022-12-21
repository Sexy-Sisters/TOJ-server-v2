package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher

interface TeacherStore {
    fun store(teacher: Teacher): Teacher
    fun delete(teacher: Teacher): Teacher
}