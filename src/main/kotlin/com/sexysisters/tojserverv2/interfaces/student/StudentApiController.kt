package com.sexysisters.tojserverv2.interfaces.student

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.student.Status
import com.sexysisters.tojserverv2.domain.student.service.StudentService
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentDtoMapper
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentRequest
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/student")
class StudentApiController(
    private val studentService: StudentService,
    private val studentDtoMapper: StudentDtoMapper,
) {

    @ApiOperation(value = "학교에 가입할 학생 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createStudent(
        @RequestBody @Valid request: StudentRequest.Create
    ): CommonResponse<Long> {
        val studentCommand = studentDtoMapper.of(request)
        val studentId = studentService.createStudent(studentCommand)
        return CommonResponse.success(studentId)
    }

    @ApiOperation(value = "학생 리스트 조회")
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    fun getWaitingList(@RequestParam("status") status: Status): CommonResponse<List<StudentResponse.Main>> {
        val studentInfo = studentService.getStudentList(status)
        val response = studentInfo.map { studentDtoMapper.of(it) }
        return CommonResponse.success(response)
    }
}