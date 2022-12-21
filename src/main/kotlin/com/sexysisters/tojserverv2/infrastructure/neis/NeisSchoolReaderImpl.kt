package com.sexysisters.tojserverv2.infrastructure.neis

import com.google.gson.Gson
import com.sexysisters.tojserverv2.common.util.api.neis.client.NeisSchoolInfoClient
import com.sexysisters.tojserverv2.common.util.api.neis.dto.NeisSchoolInfoResponse
import com.sexysisters.tojserverv2.common.util.api.neis.properties.NeisRequestProperty
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.school.domain.getDivision
import com.sexysisters.tojserverv2.domain.school.domain.getKind
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolMapper
import com.sexysisters.tojserverv2.infrastructure.neis.dto.NeisSchoolResponse
import org.springframework.stereotype.Component

@Component
class NeisSchoolReaderImpl(
    private val neisSchoolInfoClient: NeisSchoolInfoClient,
    private val neisSchoolMapper: NeisSchoolMapper,
) : NeisSchoolReader {

    override fun search(schoolName: String): List<NeisSchoolResponse> {
        val neisSchoolInfoHtml = neisSchoolInfoClient.schoolInfoByName(
            type = NeisRequestProperty.TYPE,
            pageIndex = NeisRequestProperty.PAGE_INDEX,
            pageSize = NeisRequestProperty.PAGE_SIZE,
            schoolName = schoolName,
        )
        val neisSchoolInfoResponse = Gson().fromJson(
            neisSchoolInfoHtml,
            NeisSchoolInfoResponse::class.java
        )
        val schoolInfo = neisSchoolInfoResponse.schoolInfo
            ?: throw SchoolException.SchoolNotFound()
        val row = schoolInfo[1].row
        return row.map { neisSchoolMapper.of(it) }
    }

    override fun searchByCode(code: String): NeisSchoolResponse {
        val neisSchoolInfoHtml = neisSchoolInfoClient.schoolInfoByCode(
            type = NeisRequestProperty.TYPE,
            pageIndex = NeisRequestProperty.PAGE_INDEX,
            pageSize = NeisRequestProperty.PAGE_SIZE,
            schoolCode = code,
        )

        val neisSchoolResponse = Gson().fromJson(
            neisSchoolInfoHtml,
            NeisSchoolInfoResponse::class.java
        )

        val schoolInfo = neisSchoolResponse.schoolInfo
            ?: throw SchoolException.SchoolNotFound()
        val row = schoolInfo[1].row[0]

        return neisSchoolMapper.of(row)
    }

    override fun getSchoolByCode(code: String): School {
        val schoolInfoResponse = searchByCode(code)
        val school = schoolInfoResponse.toEntity()

        val kind = schoolInfoResponse.kind
        if (kind != null) {
            school.kind = getKind(kind)
        }
        val division = schoolInfoResponse.division
        school.division = getDivision(division)

        return school
    }
}