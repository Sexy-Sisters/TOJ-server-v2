package com.sexysisters.tojserverv2.infrastructure.neis

import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoResponse

interface SchoolInfoReader {
    fun execute(schoolName: String, schoolBelong: String): List<SchoolInfoResponse>
}