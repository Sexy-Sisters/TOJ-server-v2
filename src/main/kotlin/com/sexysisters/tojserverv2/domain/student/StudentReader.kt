package com.sexysisters.tojserverv2.domain.student

import com.sexysisters.tojserverv2.domain.school.School

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