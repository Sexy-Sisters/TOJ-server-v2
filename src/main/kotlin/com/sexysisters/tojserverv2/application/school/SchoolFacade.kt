package com.sexysisters.tojserverv2.application.school

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.domain.student.service.StudentService
import com.sexysisters.tojserverv2.infrastructure.school.SchoolRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolFacade(
    private val schoolRepository: SchoolRepository,
    private val schoolService: SchoolService,
    private val studentService: StudentService,
) {

    @Transactional
    fun applySchool(command: SchoolCommand.CreateStudent, code: String): String {
        val studentId = studentService.createStudent(command)

        val isExists = schoolRepository.existsByCode(code)
        val status =
            if (isExists) {
                schoolService.applySchool(code, studentId)
            } else {
                schoolService.createSchool(code)
                schoolService.joinSchool(code, studentId)
            }
        return status
    }
}