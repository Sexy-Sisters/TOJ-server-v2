package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.domain.School
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SchoolRepository : JpaRepository<School, UUID> {
    fun existsByCodeValue(code: String): Boolean
    fun findByCodeValue(code: String): School?
}