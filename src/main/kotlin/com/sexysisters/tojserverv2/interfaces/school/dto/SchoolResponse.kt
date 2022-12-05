package com.sexysisters.tojserverv2.interfaces.school.dto

class SchoolResponse {

    data class Search(
        val code: String,
        val name: String,
        val address: String,
    )

    data class Student(
        val profileImg: String,
        val nickname: String,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}