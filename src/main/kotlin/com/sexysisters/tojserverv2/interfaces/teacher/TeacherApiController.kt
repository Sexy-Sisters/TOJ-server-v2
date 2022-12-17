package com.sexysisters.tojserverv2.interfaces.teacher

import com.sexysisters.tojserverv2.domain.teacher.service.TeacherService
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherDtoMapper
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
}