package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.school.domain.School

interface SchoolStore {
    fun store(school: School): School
}