package com.sexysisters.tojserverv2.application.school

import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.infrastructure.school.SchoolRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolFacade(
    private val schoolRepository: SchoolRepository,
    private val schoolService: SchoolService,
) {

    @Transactional
    fun applySchool(code: String): String {
        val applyStatus =
            if (!schoolRepository.existsByCode(code)) {
                schoolService.createSchool(code)
                val schoolInfo = schoolService.joinSchool(code)
                schoolInfo.applyStatus
            } else {
                val schoolInfo = schoolService.applySchool(code)
                schoolInfo.applyStatus
            }
        return applyStatus
    }
}