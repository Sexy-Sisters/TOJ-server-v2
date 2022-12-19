package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.student.domain.Student

interface SchoolPolicy {
    fun check(student: Student, school: School)
}