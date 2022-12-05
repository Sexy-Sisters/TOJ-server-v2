package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.SchoolCommand

interface StudentService {
    fun createStudent(command: SchoolCommand.CreateStudent, school: School): Long
}