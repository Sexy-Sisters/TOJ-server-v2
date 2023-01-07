package com.sexysisters.tojserverv2.interfaces.student.dto

class StudentResponse {

    data class Main(
        val id: String,
        val profileImg: String,
        val nickname: String,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}