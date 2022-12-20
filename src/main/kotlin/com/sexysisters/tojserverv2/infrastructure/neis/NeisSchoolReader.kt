package com.sexysisters.tojserverv2.infrastructure.neis

import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse

interface NeisSchoolReader {
    fun search(schoolName: String): List<NeisSchoolResponse>
    fun searchByCode(code: String): NeisSchoolResponse
    fun getSchoolByCode(code: String): School
}