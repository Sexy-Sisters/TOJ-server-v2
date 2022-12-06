package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand

interface StudentService {
    fun createStudent(command: SchoolCommand.CreateStudent): Long
}