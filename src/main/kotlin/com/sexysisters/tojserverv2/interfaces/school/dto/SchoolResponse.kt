package com.sexysisters.tojserverv2.interfaces.school.dto

class SchoolResponse {

    data class Search(
        val schoolCode: String,
        val name: String,
        val address: String,
    )
}