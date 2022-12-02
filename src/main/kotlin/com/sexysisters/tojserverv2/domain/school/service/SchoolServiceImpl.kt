package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.infrastructure.neis.SchoolInfoReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolServiceImpl(
    private val schoolInfoReader: SchoolInfoReader,
    private val schoolMapper: SchoolMapper,
) : SchoolService {

    @Transactional
    override fun searchSchool(command: SchoolCommand.SearchRequest): List<SchoolInfo.Search> {
        val name = command.name
        val belong = command.belong
        val searchResults = schoolInfoReader.execute(
            schoolName = name,
            schoolBelong = belong,
        )
        return searchResults.map { schoolMapper.of(it) }
    }
}