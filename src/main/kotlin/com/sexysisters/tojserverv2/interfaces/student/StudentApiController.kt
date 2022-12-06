package com.sexysisters.tojserverv2.interfaces.student

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.student.service.StudentService
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentDtoMapper
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentRequest
import com.sexysisters.tojserverv2.interfaces.student.dto.StudentResponse
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/student")
class StudentApiController(
    private val studentService: StudentService,
    private val studentDtoMapper: StudentDtoMapper,
) {

    @ApiOperation(value = "학교에 가입할 학생 생성")
    @PostMapping
    fun createStudent(
        @RequestBody @Valid request: StudentRequest.Create
    ): CommonResponse<Long> {
        val studentCommand = studentDtoMapper.of(request)
        val studentId = studentService.createStudent(studentCommand)
        return CommonResponse.success(studentId)
    }

    @ApiOperation(value = "가입 신청자 리스트 조회")
    @GetMapping("/waiting-list")
    fun getWaitingList(): CommonResponse<List<StudentResponse.Main>> {
        val studentInfo = studentService.getWaitingList()
        val response = studentInfo.map { studentDtoMapper.of(it) }
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "소속된 학생 리스트 조회")
    @GetMapping("/student-list")
    fun getStudentList(): CommonResponse<List<StudentResponse.Main>> {
        val schoolInfo = studentService.getStudentList()
        val response = schoolInfo.map { studentDtoMapper.of(it) }
        return CommonResponse.success(response)
    }
}