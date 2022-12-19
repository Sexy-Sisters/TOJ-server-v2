package com.sexysisters.tojserverv2.domain.wiki.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.domain.Student

interface WikiPolicy {
    fun check(student: Student, school: School)
}