package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo

interface SchoolService {
    fun searchSchool(command: SchoolCommand.Search): List<SchoolInfo.Search>
    fun createSchool(code: String)
    fun joinSchool(code: String, studentId: Long): String
    fun applySchool(code: String, studentId: Long): String
    fun getWaitingList(): List<SchoolInfo.Student>
    fun getStudentList(): List<SchoolInfo.Student>
    // fun becomeIndependent()
}