package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.domain.school.design.SchoolReader
import com.sexysisters.tojserverv2.domain.school.design.SchoolStore
import com.sexysisters.tojserverv2.domain.school.exception.SchoolExcpetion
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.setEngaged
import com.sexysisters.tojserverv2.domain.user.setRelation
import com.sexysisters.tojserverv2.domain.user.setWaiting
import com.sexysisters.tojserverv2.domain.user.type.ApplyStatus
import com.sexysisters.tojserverv2.infrastructure.neis.NeisSchoolReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolServiceImpl(
    private val neisSchoolReader: NeisSchoolReader,
    private val schoolMapper: SchoolMapper,
    private val schoolReader: SchoolReader,
    private val schoolStore: SchoolStore,
    private val userReader: UserReader,
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
        val school = neisSchoolReader.findSchoolByCode(code)
        schoolStore.store(school)
    }

    @Transactional
    override fun applySchool(code: String): SchoolInfo.Apply {
        val user = userReader.getCurrentUser()
        validateIsNone(user.applyStatus)

        val school = schoolReader.findSchoolByCode(code)
        school.studentList.add(user)
        user.school = school
        val applyStatus = user.setWaiting()
        return schoolMapper.applyOf(applyStatus)
    }

    @Transactional
    override fun joinSchool(code: String): SchoolInfo.Join {
        val user = userReader.getCurrentUser()
        validateIsNone(user.applyStatus)

        val school = schoolReader.findSchoolByCode(code)
        user.setRelation(school)
        val applyStatus = user.setEngaged()
        return schoolMapper.joinOf(applyStatus)
    }

    @Transactional(readOnly = true)
    override fun getWaitingList(): List<SchoolInfo.Student> {
        val user = userReader.getCurrentUser()
        validateIsEngaged(user.applyStatus)

        return user.school!!.studentList
            .filter { it.applyStatus != ApplyStatus.WAITING }
            .map { schoolMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getStudentList(): List<SchoolInfo.Student> {
        val user = userReader.getCurrentUser()
        validateIsEngaged(user.applyStatus)

        return user.school!!.studentList
            .filter { it.applyStatus != ApplyStatus.ENGAGED }
            .map { schoolMapper.of(it) }
    }

    private fun validateIsEngaged(applyStatus: ApplyStatus) {
        if(applyStatus != ApplyStatus.ENGAGED) {
            throw SchoolExcpetion.NotBelong()
        }
    }

    private fun validateIsNone(applyStatus: ApplyStatus) {
        if (applyStatus != ApplyStatus.NONE)
            throw SchoolExcpetion.AlreadyApply()
    }

}
