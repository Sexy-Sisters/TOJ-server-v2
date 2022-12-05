package com.sexysisters.tojserverv2.domain.school

class SchoolInfo {

    data class Main(
        val belong: String,
        val name: String,
        val englishName: String,
        val division: String,
        val address: String,
        val birthday: String,
        val homePageAddress: String,
        val phone: String,
    )

    class Search(
        val code: String,
        val name: String,
        val address: String,
    )

    class Student(
        val profileImg: String,
        val nickname: String,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}