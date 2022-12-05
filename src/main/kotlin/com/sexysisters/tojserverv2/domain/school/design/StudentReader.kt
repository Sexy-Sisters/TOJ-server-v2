package com.sexysisters.tojserverv2.domain.school.design

import com.sexysisters.tojserverv2.domain.school.student.Student

interface StudentReader {
    fun getStudent(id: Long): Student
    fun getCurrentStudent(): Student
}