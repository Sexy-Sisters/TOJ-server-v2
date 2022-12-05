package com.sexysisters.tojserverv2.domain.school.design

import com.sexysisters.tojserverv2.domain.school.School

interface SchoolReader {
    fun getSchool(code: String): School
}