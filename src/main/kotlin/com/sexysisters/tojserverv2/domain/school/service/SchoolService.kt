package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo

interface SchoolService {
    fun searchSchool(command: SchoolCommand.Search): List<SchoolInfo.Search>
    fun createSchool(code: String)
    fun applySchool(code: String): String
}