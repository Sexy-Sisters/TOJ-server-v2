package com.sexysisters.tojserverv2.application.school

import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.infrastructure.school.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolFacade(
    private val schoolRepository: SchoolRepository,
    private val schoolService: SchoolService,
) {

    fun applySchool(code: String): String {
        val isExists = schoolRepository.existsByCode(code)
        if (!isExists) {
            schoolService.createSchool(code)
        }
        return schoolService.applySchool(code)
    }
}