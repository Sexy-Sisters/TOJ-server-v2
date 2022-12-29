package com.sexysisters.tojserverv2.domain.student

class StudentInfo {

    class Main(
        val id: Long,
        val profileImg: String,
        val nickname: String,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}