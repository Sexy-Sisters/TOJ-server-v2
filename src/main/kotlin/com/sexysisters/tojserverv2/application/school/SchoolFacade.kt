package com.sexysisters.tojserverv2.application.school

import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.domain.wiki.service.WikiService
import com.sexysisters.tojserverv2.infrastructure.school.SchoolRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolFacade(
    private val schoolRepository: SchoolRepository,
    private val schoolService: SchoolService,
    private val wikiService: WikiService,
) {

    @Transactional
    fun applySchool(code: String): String {
        val isExists = schoolRepository.existsByCodeValue(code)
        if (!isExists) {
            val schoolCode = schoolService.createSchool(code)
            wikiService.createWiki(schoolCode)
        }
        return schoolService.applySchool(code)
    }
}