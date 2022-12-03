package com.sexysisters.tojserverv2.infrastructure.neis

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse

interface NeisSchoolReader {
    fun search(schoolName: String, schoolBelong: String): List<NeisSchoolResponse>
    fun searchByCode(code: String): NeisSchoolResponse
    fun findSchoolByCode(code: String): School
}