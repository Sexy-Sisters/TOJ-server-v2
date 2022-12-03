package com.sexysisters.tojserverv2.interfaces.school

import com.sexysisters.tojserverv2.application.school.SchoolFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolDtoMapper
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolRequest
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/school")
class SchoolApiController(
    private val schoolDtoMapper: SchoolDtoMapper,
    private val schoolService: SchoolService,
    private val schoolFacade: SchoolFacade,
) {

    @GetMapping("/list")
    fun searchSchool(
        @RequestBody @Valid request: SchoolRequest.Search
    ): CommonResponse<List<SchoolResponse.Search>> {
        val schoolCommand = schoolDtoMapper.of(request)
        val schoolInfo = schoolService.searchSchool(schoolCommand)
        val response = schoolInfo.map { schoolDtoMapper.of(it) }
        return CommonResponse.success(response)
    }

    @PostMapping
    fun applySchool(
        @RequestParam(name = "schoolCode") schoolCode: String
    ): CommonResponse<String> {
        val applyStaus = schoolFacade.applySchool(schoolCode)
        return CommonResponse.success(applyStaus)
    }
}