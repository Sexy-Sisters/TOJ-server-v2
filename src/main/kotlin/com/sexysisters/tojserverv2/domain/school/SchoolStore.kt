package com.sexysisters.tojserverv2.domain.school

interface SchoolStore {
    fun store(school: School): School
}