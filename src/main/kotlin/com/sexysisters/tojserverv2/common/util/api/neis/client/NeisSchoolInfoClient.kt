package com.sexysisters.tojserverv2.common.util.api.neis.client

import com.sexysisters.tojserverv2.common.util.api.neis.properties.NeisParamProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "SchoolInfoClient",
    url = "https://open.neis.go.kr/hub"
)
interface NeisSchoolInfoClient {

    @GetMapping("/schoolInfo")
    fun schoolInfo(
        @RequestParam(NeisParamProperty.TYPE) type: String,
        @RequestParam(NeisParamProperty.PAGE_INDEX) pageIndex: Int,
        @RequestParam(NeisParamProperty.PAGE_SIZE) pageSize: Int,
        @RequestParam(NeisParamProperty.SCHOOL_NAME) schoolName: String,
        @RequestParam(NeisParamProperty.SCHOOL_BELONG) schoolBelong: String,
    ): String
}