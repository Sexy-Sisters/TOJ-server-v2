package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Status
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.StudentStore
import com.sexysisters.tojserverv2.domain.student.updateStatus
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    private val userReader: UserReader,
    private val studentStore: StudentStore,
    private val schoolReader: SchoolReader,
    private val studentReader: StudentReader,
) : StudentService {

    @Transactional
    override fun createStudent(
        command: SchoolCommand.CreateStudent,
    ): Long {
        val user = userReader.getCurrentUser()
        if (user.student != null) {
            validateIsNonEngaged(user.student!!)
        }
        val initStudent = Student(
            user = user,
            grade = command.grade,
            classroom = command.classroom,
            number = command.number,
            age = command.age
        )
        user.student = initStudent

        return studentStore.store(initStudent)
    }

    private fun validateIsNonEngaged(student: Student) {
        if (student.status == Status.WAITING) {
            throw SchoolException.AlreadyApplied()
        }
        if (student.status == Status.ENGAGED) {
            throw SchoolException.AlreadyJoined()
        }
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
}