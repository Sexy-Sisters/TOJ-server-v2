package com.sexysisters.tojserverv2.domain.student

interface StudentStore {
    fun store(student: Student): Long
}