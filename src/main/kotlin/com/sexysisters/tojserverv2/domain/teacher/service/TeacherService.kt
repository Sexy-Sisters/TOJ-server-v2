package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.teacher.TeacherCommand

interface TeacherService {
    fun createTeacher(command: TeacherCommand.Create)
}