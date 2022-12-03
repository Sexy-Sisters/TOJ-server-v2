package com.sexysisters.tojserverv2.domain.school.design

import com.sexysisters.tojserverv2.domain.school.School

interface SchoolStore {
    fun store(school: School): School
}