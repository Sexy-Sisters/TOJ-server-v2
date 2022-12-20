package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.school.domain.School

interface SchoolReader {
    fun getSchool(code: String): School
}