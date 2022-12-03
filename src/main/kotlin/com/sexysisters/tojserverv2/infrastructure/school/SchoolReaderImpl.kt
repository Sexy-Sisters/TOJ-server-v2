package com.sexysisters.tojserverv2.infrastructure.school

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.design.SchoolReader
import com.sexysisters.tojserverv2.domain.school.type.getDivision
import com.sexysisters.tojserverv2.domain.school.type.getKind
import com.sexysisters.tojserverv2.infrastructure.neis.SchoolInfoReader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolReaderImpl(
    private val schoolInfoReader: SchoolInfoReader,
) : SchoolReader {

    @Transactional
    override fun findSchoolByCode(code: String): School {
        val schoolInfoResponse = schoolInfoReader.findSchoolByCode(code)
        val school = schoolInfoResponse.toEntity()

        val kind = schoolInfoResponse.kind
        school.kind = getKind(kind)
        val division = schoolInfoResponse.division
        school.division = getDivision(division)

        return school
    }
}