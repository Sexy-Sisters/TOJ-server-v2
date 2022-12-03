package com.sexysisters.tojserverv2.infrastructure.neis

import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoResponse

interface SchoolInfoReader {
    fun searchSchools(schoolName: String, schoolBelong: String): List<SchoolInfoResponse>
    fun findSchoolByCode(code: String): SchoolInfoResponse
}