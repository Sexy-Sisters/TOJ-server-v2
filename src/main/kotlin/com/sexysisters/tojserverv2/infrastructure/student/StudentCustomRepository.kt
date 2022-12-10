package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.school.School

interface StudentCustomRepository {
    fun checkAlreadyExists(
        school: School,
        grade: Int,
        classroom: Int,
        number: Int
    ): Boolean
}