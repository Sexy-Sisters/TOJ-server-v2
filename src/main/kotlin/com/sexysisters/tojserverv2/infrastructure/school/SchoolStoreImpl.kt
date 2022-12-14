package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.school.SchoolStore
import org.springframework.stereotype.Component

@Component
class SchoolStoreImpl(
    private val schoolRepository: SchoolRepository,
) : SchoolStore {
    override fun store(school: School) = schoolRepository.save(school)
}