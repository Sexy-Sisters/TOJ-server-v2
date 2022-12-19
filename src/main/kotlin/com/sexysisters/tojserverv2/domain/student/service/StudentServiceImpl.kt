package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.student.*
import com.sexysisters.tojserverv2.domain.student.domain.*
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
        val initStudent = createStudent(
            grade = command.grade,
            classroom = command.classroom,
            number = command.number,
            age = command.age,
        )
        initStudent.makeRelation(user)
        return studentStore.store(initStudent)
    }

    private fun createStudent(
        grade: Int,
        classroom: Int,
        number: Int,
        age: Int,
    ) = Student(
        grade = Grade(grade),
        classroom = Classroom(classroom),
        number = Number(number),
        age = Age(age)
    )

    @Transactional(readOnly = true)
    override fun getStudentList(status: Status): List<StudentInfo.Main> {
        val student = studentReader.getCurrentStudent()
        student.school ?: throw StudentException.NotBelong()
        return studentReader.getStudentList(student.school!!, status)
            .map { studentMapper.of(it) }
    }
}