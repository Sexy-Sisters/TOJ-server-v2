package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolInfo

interface SchoolService {
    fun searchSchool(name: String): List<SchoolInfo.Search>
    fun createSchool(code: String)
    fun applySchool(code: String): String
}