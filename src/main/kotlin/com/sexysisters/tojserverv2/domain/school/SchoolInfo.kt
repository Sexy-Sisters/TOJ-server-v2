package com.sexysisters.tojserverv2.domain.school

class SchoolInfo {

    data class Search(
        val schoolCode: String,
        val name: String,
        val address: String,
    )
}