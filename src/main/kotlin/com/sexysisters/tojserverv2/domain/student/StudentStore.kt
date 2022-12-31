package com.sexysisters.tojserverv2.domain.student

import com.sexysisters.tojserverv2.domain.student.domain.Student

interface StudentStore {
    fun store(student: Student): Student
}