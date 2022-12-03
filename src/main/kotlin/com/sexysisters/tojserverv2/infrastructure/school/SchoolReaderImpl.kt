package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.design.SchoolReader
import com.sexysisters.tojserverv2.domain.school.exception.SchoolExcpetion
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolReaderImpl(
    private val schoolRepository: SchoolRepository,
) : SchoolReader {

    @Transactional
    override fun findSchoolByCode(code: String): School {
        return schoolRepository.findByCode(code)
            ?: throw SchoolExcpetion.SchoolNotFound()
    }
}