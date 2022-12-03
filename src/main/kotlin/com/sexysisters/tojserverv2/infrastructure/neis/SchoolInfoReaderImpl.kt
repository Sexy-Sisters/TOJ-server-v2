package com.sexysisters.tojserverv2.infrastructure.neis

import com.google.gson.Gson
import com.sexysisters.tojserverv2.common.util.api.neis.client.NeisSchoolInfoClient
import com.sexysisters.tojserverv2.common.util.api.neis.dto.NeisSchoolInfoResponse
import com.sexysisters.tojserverv2.common.util.api.neis.properties.NeisRequestProperty
import com.sexysisters.tojserverv2.domain.school.exception.SchoolNotFoundException
import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoMapper
import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoResponse
import org.springframework.stereotype.Component

@Component
class SchoolInfoReaderImpl(
    private val neisSchoolInfoClient: NeisSchoolInfoClient,
    private val schoolInfoMapper: SchoolInfoMapper,
) : SchoolInfoReader {

    override fun searchSchools(schoolName: String, schoolBelong: String): List<SchoolInfoResponse> {
        val neisSchoolInfoHtml = neisSchoolInfoClient.schoolInfo(
            type = NeisRequestProperty.TYPE,
            pageIndex = NeisRequestProperty.PAGE_INDEX,
            pageSize = NeisRequestProperty.PAGE_SIZE,
            schoolName = schoolName,
            schoolBelong = schoolBelong,
        )
        val neisSchoolInfoResponse = Gson().fromJson(
            neisSchoolInfoHtml,
            NeisSchoolInfoResponse::class.java
        )

        val schoolInfo = neisSchoolInfoResponse.schoolInfo
            ?: throw SchoolNotFoundException()
        val row = schoolInfo[1].row

        return row.map { schoolInfoMapper.of(it) }
    }

    override fun findSchoolByCode(code: String): SchoolInfoResponse {
        val neisSchoolInfoHtml = neisSchoolInfoClient.schoolInfo(
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
            ?: throw SchoolNotFoundException()
        val row = schoolInfo[1].row[0]

        return schoolInfoMapper.of(row)
    }
}