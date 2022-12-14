package com.sexysisters.tojserverv2.domain.school.service

import com.sexysisters.tojserverv2.domain.school.SchoolCommand
import com.sexysisters.tojserverv2.domain.school.SchoolInfo

interface SchoolService {
    fun searchSchool(name: String): List<SchoolInfo.Search>
    fun createSchool(code: String): String
    fun applySchool(): String
    fun updateWallpaper(command: SchoolCommand.UpdateWallpaper): String
}