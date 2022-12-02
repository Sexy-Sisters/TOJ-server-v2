package com.sexysisters.tojserverv2.infrastructure.neis

import com.google.gson.Gson
import com.sexysisters.tojserverv2.common.util.api.neis.client.NeisSchoolInfoClient
import com.sexysisters.tojserverv2.common.util.api.neis.properties.NeisRequestProperty
import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoMapper
import com.sexysisters.tojserverv2.infrastructure.neis.dto.SchoolInfoResponse
import org.springframework.stereotype.Component

@Component
class SchoolInfoReaderImpl(
    private val neisSchoolInfoClient: NeisSchoolInfoClient,
    private val schoolInfoMapper: SchoolInfoMapper,
) : SchoolInfoReader {

    override fun execute(schoolName: String, schoolDivision: String): List<SchoolInfoResponse> {
        val neisSchoolInfoHtml = neisSchoolInfoClient.schoolInfo(
            type = NeisRequestProperty.TYPE,
            pageIndex = NeisRequestProperty.PAGE_INDEX,
            pageSize = NeisRequestProperty.PAGE_SIZE,
            schoolName = schoolName,
            schoolDivision = schoolDivision,
        )
        val neisSchoolInfoResponse = Gson().fromJson(
            neisSchoolInfoHtml,
            NeisSchoolInfoResponse::class.java
        )

        return neisSchoolInfoResponse.schoolInfo[1].row
            .map { schoolInfoMapper.of(it) }
    }
}