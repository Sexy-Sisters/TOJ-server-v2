package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.domain.School
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, String> {
    fun existsByCodeValue(code: String): Boolean
    fun findByCodeValue(code: String): School?
}