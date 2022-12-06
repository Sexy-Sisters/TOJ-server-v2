package com.sexysisters.tojserverv2.domain.student

interface StudentReader {
    fun getStudent(id: Long): Student
    fun getCurrentStudent(): Student
}