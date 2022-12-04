package com.sexysisters.tojserverv2.interfaces.school

import com.sexysisters.tojserverv2.application.school.SchoolFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolDtoMapper
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolRequest
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["School 관련 API"])
@RestController
@RequestMapping("/api/v2/school")
class SchoolApiController(
    private val schoolDtoMapper: SchoolDtoMapper,
    private val schoolService: SchoolService,
    private val schoolFacade: SchoolFacade,
) {

    @ApiOperation(value = "학교 검색")
    @GetMapping
    fun searchSchool(
        @RequestBody @Valid request: SchoolRequest.Search
    ): CommonResponse<List<SchoolResponse.Search>> {
        val schoolCommand = schoolDtoMapper.of(request)
        val schoolInfo = schoolService.searchSchool(schoolCommand)
        val response = schoolInfo.map { schoolDtoMapper.of(it) }
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "학교 가입 신청(새로 생성하는 학교면 바로 가입)")
    @PostMapping
    fun applySchool(
        @RequestParam(name = "schoolCode") schoolCode: String
    ): CommonResponse<String> {
        val applyStaus = schoolFacade.applySchool(schoolCode)
        return CommonResponse.success(applyStaus)
    }

    @ApiOperation(value = "가입 신청자 리스트 조회")
    @GetMapping("/waiting-list")
    fun getWaitingList(): CommonResponse<List<SchoolResponse.Student>> {
        val schoolInfo = schoolService.getWaitingList()
        val response = schoolInfo.map { schoolDtoMapper.of(it) }
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "소속된 학생 리스트 조회")
    @GetMapping("/student-list")
    fun getStudentList(): CommonResponse<List<SchoolResponse.Student>> {
        val schoolInfo = schoolService.getStudentList()
        val response = schoolInfo.map { schoolDtoMapper.of(it) }
        return CommonResponse.success(response)
    }
}