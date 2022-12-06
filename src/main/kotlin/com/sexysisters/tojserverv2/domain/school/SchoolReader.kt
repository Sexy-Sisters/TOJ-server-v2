package com.sexysisters.tojserverv2.domain.school

interface SchoolReader {
    fun getSchool(code: String): School
}