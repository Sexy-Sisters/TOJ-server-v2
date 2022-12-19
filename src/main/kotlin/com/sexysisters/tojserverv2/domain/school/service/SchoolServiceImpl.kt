package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.school.SchoolStore
import com.sexysisters.tojserverv2.domain.school.policy.SchoolPolicy
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.infrastructure.neis.NeisSchoolReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolServiceImpl(
    private val neisSchoolReader: NeisSchoolReader,
    private val schoolMapper: SchoolMapper,
    private val schoolStore: SchoolStore,
    private val studentReader: StudentReader,
    private val schoolReader: SchoolReader,
    private val schoolPolicy: List<SchoolPolicy>,
) : SchoolService {

    @Transactional(readOnly = true)
    override fun searchSchool(name: String): List<SchoolInfo.Search> {
        val searchResults = neisSchoolReader.search(name)
        return searchResults.map { schoolMapper.of(it) }
    }

    @Transactional
    override fun createSchool(code: String): String {
        val initSchool = neisSchoolReader.getSchoolByCode(code)
        return schoolStore.store(initSchool).code
    }

    @Transactional
    override fun applySchool(code: String): String {
        val student = studentReader.getCurrentStudent()
        val school = schoolReader.getSchool(code)
        schoolPolicy.forEach { it.check(student, school) }
        school.makeRelation(student)
        return student.status.description
    }
}