package com.sexysisters.tojserverv2.interfaces.teacher

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.teacher.service.TeacherService
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherDtoMapper
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherRequest
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["선생님 관련 API"])
@RestController
@RequestMapping("/api/v2/teacher")
class TeacherApiController(
    private val teacherService: TeacherService,
    private val teacherDtoMapper: TeacherDtoMapper,
) {

    @ApiOperation(value = "선생님 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createTeacher(
        @RequestBody @Valid request: TeacherRequest.Create
    ) {
        val teacherCommand = teacherDtoMapper.of(request)
        teacherService.createTeacher(teacherCommand)
    }

    @ApiOperation(value = "학교 코드로 선생님들 조회")
    @GetMapping
    fun getTeachers(
        @RequestParam("code") schoolCode: String
    ): CommonResponse<List<TeacherResponse.Search>> {
        val response = teacherService.getTeachers(schoolCode)
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "특정 학교에 속한 학생만 그 학교의 선생님들 조회")
    @GetMapping("/{id}")
    fun getTeacher(
        @PathVariable("id") id: Long,
    ): CommonResponse<TeacherResponse.Get> {
        val response = teacherService.getTeacher(id)
        return CommonResponse.success(response)
    }
}