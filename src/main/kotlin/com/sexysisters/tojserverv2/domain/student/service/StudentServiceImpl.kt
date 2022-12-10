package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.student.Status
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.StudentCommand
import com.sexysisters.tojserverv2.domain.student.StudentInfo
import com.sexysisters.tojserverv2.domain.student.StudentMapper
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.StudentStore
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.student.makeRelation
import com.sexysisters.tojserverv2.domain.student.policy.StudentPolicy
import com.sexysisters.tojserverv2.domain.user.design.UserReader
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
        val initStudent = Student(
            grade = command.grade,
            classroom = command.classroom,
            number = command.number,
            age = command.age,
        )
        initStudent.makeRelation(user)
        return studentStore.store(initStudent)
    }

    @Transactional(readOnly = true)
    override fun getWaitingList(): List<StudentInfo.Main> {
        val student = studentReader.getCurrentStudent()
        student.school ?: throw StudentException.NotBelong()
        return student.school!!.studentList
            .filter { it.status == Status.WAITING }
            .map { studentMapper.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getStudentList(): List<StudentInfo.Main> {
        val student = studentReader.getCurrentStudent()
        student.school ?: throw StudentException.NotBelong()
        return student.school!!.studentList
            .filter { it.status == Status.ENGAGED }
            .map { studentMapper.of(it) }
    }
}