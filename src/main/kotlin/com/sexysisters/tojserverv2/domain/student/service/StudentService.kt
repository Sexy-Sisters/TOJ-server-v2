package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand

interface StudentService {
    fun createStudent(command: SchoolCommand.CreateStudent): Long
    fun joinSchool(code: String, studentId: Long): String
    fun applySchool(code: String, studentId: Long): String
}