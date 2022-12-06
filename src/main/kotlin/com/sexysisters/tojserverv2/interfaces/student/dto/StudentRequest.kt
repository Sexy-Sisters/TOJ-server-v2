package com.sexysisters.tojserverv2.interfaces.student.dto

class StudentRequest {

    data class Create(
        val age: Int,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}