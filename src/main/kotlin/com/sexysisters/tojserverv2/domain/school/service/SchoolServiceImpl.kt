package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo
import com.sexysisters.tojserverv2.domain.school.SchoolMapper
import com.sexysisters.tojserverv2.domain.school.design.SchoolReader
import com.sexysisters.tojserverv2.domain.school.design.SchoolStore
import com.sexysisters.tojserverv2.domain.school.design.StudentReader
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.school.student.Status
import com.sexysisters.tojserverv2.domain.school.student.Student
import com.sexysisters.tojserverv2.domain.school.student.updateStatus
import com.sexysisters.tojserverv2.infrastructure.neis.NeisSchoolReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolServiceImpl(
    private val neisSchoolReader: NeisSchoolReader,
    private val schoolMapper: SchoolMapper,
    private val schoolReader: SchoolReader,
    private val schoolStore: SchoolStore,
    private val studentReader: StudentReader,
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
    override fun applySchool(code: String, studentId: Long): String {
        val student = studentReader.getStudent(studentId)
        val school = schoolReader.getSchool(code)
        school.studentList.add(student)
        student.school = school
        return student.updateStatus(Status.WAITING)
    }

    @Transactional
    override fun joinSchool(code: String, studentId: Long): String {
        val student = studentReader.getStudent(studentId)
        val school = schoolReader.getSchool(code)
        school.studentList.add(student)
        student.school = school
        return student.updateStatus(Status.ENGAGED)
    }

    @Transactional(readOnly = true)
    override fun getWaitingList(): List<SchoolInfo.Student> {
        val student = studentReader.getCurrentStudent()
        student.school ?: throw SchoolException.NotBelong()

        return student.school!!.studentList
            .filter { it.status == Status.WAITING }
            .map { schoolMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getStudentList(): List<SchoolInfo.Student> {
        val student = studentReader.getCurrentStudent()
        student.school ?: throw SchoolException.NotBelong()

        return student.school!!.studentList
            .filter { it.status == Status.ENGAGED }
            .map { schoolMapper.of(it) }
    }
}