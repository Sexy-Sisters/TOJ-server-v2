package com.sexysisters.tojserverv2.domain.student

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.student.domain.Status
import com.sexysisters.tojserverv2.domain.student.domain.Student

interface StudentReader {
    fun getStudent(id: Long): Student
    fun getCurrentStudent(): Student
    fun checkAlreadyExists(
        school: School,
        grade: Int,
        classroom: Int,
        number: Int
    ): Boolean
    fun getStudentList(school: School, status: Status): List<Student>
}