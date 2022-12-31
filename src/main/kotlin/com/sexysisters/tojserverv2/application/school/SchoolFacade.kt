package com.sexysisters.tojserverv2.application.school

import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.domain.student.StudentCommand
import com.sexysisters.tojserverv2.domain.student.service.StudentService
import com.sexysisters.tojserverv2.domain.wiki.service.WikiService
import com.sexysisters.tojserverv2.infrastructure.school.SchoolRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SchoolFacade(
    private val schoolRepository: SchoolRepository,
    private val schoolService: SchoolService,
    private val wikiService: WikiService,
    private val studentService: StudentService,
) {

    @Transactional
    fun applySchool(command: StudentCommand.Create): String {
        val code = command.schoolCode
        val isExists = schoolRepository.existsByCodeValue(code)
        if (!isExists) {
            schoolService.createSchool(code)
            wikiService.createWiki(code)
        }
        studentService.createStudent(command)
        return schoolService.applySchool()
    }
}