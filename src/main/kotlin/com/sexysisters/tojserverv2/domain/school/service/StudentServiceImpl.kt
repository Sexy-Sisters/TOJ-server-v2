package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.design.StudentStore
import com.sexysisters.tojserverv2.domain.school.student.Student
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    private val userReader: UserReader,
    private val studentStore: StudentStore,
) : StudentService {

    @Transactional
    override fun createStudent(
        command: SchoolCommand.CreateStudent,
        school: School,
    ): Long {
        val user = userReader.getCurrentUser()
        val initStudent = Student(
            user = user,
            school = school,
            grade = command.grade,
            classroom = command.classroom,
            number = command.number,
            age = command.age
        )
        user.student = initStudent
        school.studentList.add(initStudent)

        return studentStore.store(initStudent)
    }
}