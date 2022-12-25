package com.sexysisters.tojserverv2.interfaces.school.dto

import javax.validation.constraints.NotBlank

class SchoolRequest {

    data class Search(
        @field: NotBlank(message = "school name is empty")
        val name: String,

        @field: NotBlank(message = "school belong is empty")
        val belong: String,
    )

    data class UpdateWallpaper(
        @field: NotBlank(message = "wallpaper is empty")
        val wallpaper: String
    )
}