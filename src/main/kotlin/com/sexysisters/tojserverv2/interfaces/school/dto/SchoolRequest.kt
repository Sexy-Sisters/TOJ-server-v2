package com.sexysisters.tojserverv2.interfaces.school.dto

import javax.validation.constraints.NotBlank

class SchoolRequest {

    data class Apply(
        val schoolCode: String,
        val age: Int,
        val grade: Int,
        val classroom: Int,
        val number: Int,
    )

    data class Search(
        @field: NotBlank(message = "school name is empty")
        val name: String,

        @field: NotBlank(message = "school beString is empty")
        val beString: String,
    )

    data class UpdateWallpaper(
        @field: NotBlank(message = "wallpaper is empty")
        val wallpaper: String
    )
}