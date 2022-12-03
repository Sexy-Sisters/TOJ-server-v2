package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.domain.school.design.SchoolReader
import com.sexysisters.tojserverv2.domain.school.design.SchoolStore
import com.sexysisters.tojserverv2.domain.user.setNone
import com.sexysisters.tojserverv2.domain.user.setRelation
import com.sexysisters.tojserverv2.infrastructure.neis.SchoolInfoReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolServiceImpl(
    private val schoolInfoReader: SchoolInfoReader,
    private val schoolMapper: SchoolMapper,
    private val schoolReader: SchoolReader,
    private val schoolStore: SchoolStore,
) : SchoolService {

    @Transactional
    override fun searchSchool(command: SchoolCommand.SearchRequest): List<SchoolInfo.Search> {
        val name = command.name
        val belong = command.belong
        val searchResults = schoolInfoReader.searchSchools(
            schoolName = name,
            schoolBelong = belong,
        )
        return searchResults.map { schoolMapper.of(it) }
    }

    @Transactional
    override fun createSchool(code: String) {
        val school = schoolReader.findSchoolByCode(code)
        schoolStore.store(school)
    }
}