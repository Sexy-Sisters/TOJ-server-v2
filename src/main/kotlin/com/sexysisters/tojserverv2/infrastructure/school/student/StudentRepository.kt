package com.sexysisters.tojserverv2.infrastructure.school.student

import com.sexysisters.tojserverv2.domain.student.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>