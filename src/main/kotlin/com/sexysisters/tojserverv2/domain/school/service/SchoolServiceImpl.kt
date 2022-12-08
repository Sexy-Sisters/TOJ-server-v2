package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.school.SchoolStore
import com.sexysisters.tojserverv2.domain.school.makeRelation
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.engaged
import com.sexysisters.tojserverv2.domain.student.waiting
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
) : SchoolService {

    @Transactional(readOnly = true)
    override fun searchSchool(command: SchoolCommand.Search): List<SchoolInfo.Search> {
        val searchResults = neisSchoolReader.search(
            schoolName = command.name,
            schoolBelong = command.belong,
        )
        return searchResults.map { schoolMapper.of(it) }
    }

    @Transactional
    override fun createSchool(code: String) {
        val initSchool = neisSchoolReader.getSchoolByCode(code)
        schoolStore.store(initSchool)
    }

    @Transactional
    override fun applySchool(code: String): String {
        val student = studentReader.getCurrentStudent()
        val school = schoolReader.getSchool(code)
        school.makeRelation(student)
        student.waiting()
        return student.status.description
    }

    @Transactional
    override fun joinSchool(code: String): String {
        val student = studentReader.getCurrentStudent()
        val school = schoolReader.getSchool(code)
        school.makeRelation(student)
        student.engaged()
        return student.status.description
    }
}