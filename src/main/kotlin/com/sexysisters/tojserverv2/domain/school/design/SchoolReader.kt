package com.sexysisters.tojserverv2.domain.school.design

import com.sexysisters.tojserverv2.domain.school.School

interface SchoolReader {
    fun findSchoolByCode(code: String): School
}