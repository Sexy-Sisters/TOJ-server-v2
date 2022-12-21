package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolReaderImpl(
    private val schoolRepository: SchoolRepository,
) : SchoolReader {

    @Transactional
    override fun getSchool(code: String) =
        schoolRepository.findByCodeValue(code)
            ?: throw SchoolException.SchoolNotFound()
}