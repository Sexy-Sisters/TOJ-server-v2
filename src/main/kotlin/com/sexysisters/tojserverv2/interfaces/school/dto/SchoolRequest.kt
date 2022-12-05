package com.sexysisters.tojserverv2.interfaces.school.dto

import javax.validation.constraints.NotBlank

class SchoolRequest {

    data class Search(
        @field: NotBlank(message = "school name is empty")
        val name: String,

        @field: NotBlank(message = "school belong is empty")
        val belong: String,
    )

    class CreateStudent(
        val age: Int,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )
}