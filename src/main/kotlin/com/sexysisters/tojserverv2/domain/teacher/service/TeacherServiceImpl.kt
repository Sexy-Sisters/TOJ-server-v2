package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.domain.isAttendSchool
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.teacher.*
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.hasStudent
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherRequest
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeacherServiceImpl(
    private val userReader: UserReader,
    private val teacherStore: TeacherStore,
    private val teacherReader: TeacherReader,
    private val teacherEntityMapper: TeacherEntityMapper,
    private val teacherResponseMapper: TeacherResponseMapper,
) : TeacherService {

    override fun create(command: TeacherCommand.Create) {
        checkStudentIdentity()
        val teacher = teacherEntityMapper.of(command)
        teacherStore.store(teacher)
    }

    @Transactional(readOnly = true)
    override fun getTeachers(schoolCode: String): List<TeacherResponse.Search> {
        val teachers = teacherReader.search(schoolCode)
        return teachers.map { teacherResponseMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getTeacher(id: Long): TeacherResponse.Get {
        val teacher = teacherReader.getTeacher(id)
        return teacherResponseMapper.ofDetail(teacher)
    }

    override fun update(id: Long, request: TeacherCommand.Update) {
        val student = checkStudentIdentity()
        if(!student.isAttendSchool()) throw SchoolException.SchoolNotFound()
        val teacher = teacherReader.getTeacher(id, student.school!!)
        teacher.update(request.image, request.name, request.nickname, request.bio)
    }

    override fun delete(id: Long) {
        val student = checkStudentIdentity()
        if(!student.isAttendSchool()) throw SchoolException.SchoolNotFound()
        val teacher = teacherReader.getTeacher(id, student.school!!)
        teacherStore.delete(teacher)
    }

    private fun checkStudentIdentity(): Student {
        val user = userReader.getCurrentUser()
        if (!user.hasStudent()) throw StudentException.StudentNotFound()
        return user.student!!
    }
}