package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.teacher.*
import com.sexysisters.tojserverv2.domain.teacher.domain.*
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherDtoMapper
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeacherServiceImpl(
    private val userReader: UserReader,
    private val teacherStore: TeacherStore,
    private val teacherReader: TeacherReader,
    private val teacherDtoMapper: TeacherDtoMapper,
) : TeacherService {

    override fun create(command: TeacherCommand.Create) {
        checkStudentIdentity()
        val teacher = createTeacherEntity(command)
        teacherStore.store(teacher)
    }

    @Transactional(readOnly = true)
    override fun getTeachers(schoolCode: String): List<TeacherResponse.Main> {
        val teachers = teacherReader.search(schoolCode)
        return teachers.map { teacherDtoMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getTeacher(id: Long): TeacherResponse.Main {
        val teacher = teacherReader.getTeacher(id)
        return teacherDtoMapper.of(teacher)
    }

    override fun update(id: Long, command: TeacherCommand.Update) {
        val student = checkStudentIdentity()
        if (!student.isAttendSchool()) throw SchoolException.SchoolNotFound()
        val teacher = teacherReader.getTeacher(id, student.school!!)
        teacher.update(
            image = Image(command.image),
            name = Name(command.name),
            nickname = Nickname(command.nickname),
            bio = Bio(command.bio)
        )
    }

    override fun delete(id: Long) {
        val student = checkStudentIdentity()
        if (!student.isAttendSchool()) throw SchoolException.SchoolNotFound()
        val teacher = teacherReader.getTeacher(id, student.school!!)
        teacherStore.delete(teacher)
    }

    private fun createTeacherEntity(command: TeacherCommand.Create) = Teacher(
        Image(command.image),
        Name(command.name),
        Nickname(command.nickname),
        Bio(command.bio),
    )

    private fun checkStudentIdentity(): Student {
        val user = userReader.getCurrentUser()
        if (!user.hasStudent()) throw StudentException.StudentNotFound()
        return user.student!!
    }
}