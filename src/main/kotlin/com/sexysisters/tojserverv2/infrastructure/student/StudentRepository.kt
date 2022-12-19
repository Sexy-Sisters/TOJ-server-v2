package com.sexysisters.tojserverv2.infrastructure.student

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.student.domain.Status
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>, StudentCustomRepository {
    fun findAllBySchoolAndStatus(school: School, status: Status): List<Student>
}