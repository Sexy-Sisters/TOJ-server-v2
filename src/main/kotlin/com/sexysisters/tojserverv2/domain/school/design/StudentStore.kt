package com.sexysisters.tojserverv2.domain.school.design

import com.sexysisters.tojserverv2.domain.school.student.Student

interface StudentStore {
    fun store(student: Student): Long
}