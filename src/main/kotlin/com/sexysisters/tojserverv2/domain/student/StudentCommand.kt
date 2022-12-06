package com.sexysisters.tojserverv2.domain.student

class StudentCommand {

    data class Create(
        val age: Int,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}