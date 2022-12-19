package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.domain.School
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): School?
}