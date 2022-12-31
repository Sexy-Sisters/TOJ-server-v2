package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.student.*
import com.sexysisters.tojserverv2.domain.student.domain.*
import com.sexysisters.tojserverv2.domain.student.domain.Number
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.student.policy.StudentPolicy
import com.sexysisters.tojserverv2.domain.user.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    private val userReader: UserReader,
    private val studentStore: StudentStore,
    private val studentReader: StudentReader,
    private val studentMapper: StudentMapper,
    private val studentPolicy: List<StudentPolicy>,
) : StudentService {

    @Transactional
    override fun createStudent(command: StudentCommand.Create): Long {
        val user = userReader.getCurrentUser()
        studentPolicy.forEach { it.check(user) }
        val initStudent = createStudentEntity(command)
        val initStudent = Student(
            user = user,
            school = school,

    private fun createStudentEntity(command: StudentCommand.Create) = Student(
            grade = Grade(command.grade),
            classroom = Classroom(command.classroom),
            number = Number(command.number),
        age = Age(command.age),
    )

    @Transactional(readOnly = true)
    override fun getStudentList(status: Status): List<StudentInfo.Main> {
        val student = studentReader.getCurrentStudent()
        return studentReader.getStudentList(student.school, status)
            .map { studentMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getCurrentStudent(): StudentInfo.Main {
        val student = studentReader.getCurrentStudent()
        return studentMapper.of(student)
    }
}