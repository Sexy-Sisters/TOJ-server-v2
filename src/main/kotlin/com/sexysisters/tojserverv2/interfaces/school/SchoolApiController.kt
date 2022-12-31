package com.sexysisters.tojserverv2.interfaces.school

import com.sexysisters.tojserverv2.application.school.SchoolFacade
import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.school.service.SchoolService
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolDtoMapper
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolRequest
import com.sexysisters.tojserverv2.interfaces.school.dto.SchoolResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["학교 관련 API"])
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
        @RequestParam(name = "name") name: String,
    ): CommonResponse<List<SchoolResponse.Search>> {
        val schoolInfo = schoolService.searchSchool(name)
        val response = schoolInfo.map { schoolDtoMapper.of(it) }
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "학교 가입 신청(새로 생성하는 학교면 바로 가입)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createStudent(@RequestBody @Valid request: SchoolRequest.Apply): CommonResponse<String> {
        val studentCommand = schoolDtoMapper.of(request)
        val status = schoolFacade.applySchool(studentCommand)
        return CommonResponse.success(status)
    }

    @ApiOperation(value = "학교 배경화면 수정")
    @PutMapping("/wallpaper")
    fun applySchool(
        @RequestBody @Valid request: SchoolRequest.UpdateWallpaper
    ): CommonResponse<String> {
        val schoolCommand = schoolDtoMapper.of(request)
        val updatedWallpaper = schoolService.updateWallpaper(schoolCommand)
        return CommonResponse.success(updatedWallpaper)
    }
}