package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.Student

interface SchoolPolicy {
    fun check(student: Student, school: School)
}