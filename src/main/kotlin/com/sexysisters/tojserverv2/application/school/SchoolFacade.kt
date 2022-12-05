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
        val isExists = schoolRepository.existsByCode(code)

        val applyStatus =
            if (isExists) {
                schoolService.applySchool(code)
            } else {
                schoolService.createSchool(code)
                schoolService.joinSchool(code)
            }
        return applyStatus
    }
}