package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.Status
import com.sexysisters.tojserverv2.domain.student.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>, StudentCustomRepository {
    fun findAllBySchoolAndStatus(school: School, status: Status): List<Student>
}