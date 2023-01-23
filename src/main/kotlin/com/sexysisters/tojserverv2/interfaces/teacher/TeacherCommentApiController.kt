package com.sexysisters.tojserverv2.interfaces.teacher

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.teacher.service.TeacherCommentService
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentDtoMapper
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentRequest
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Api(tags = ["선생님 댓글 관련 API"])
@RestController
@RequestMapping("/api/v2/teacher/comment")
class TeacherCommentApiController(
    private val teacherCommentService: TeacherCommentService,
    private val teacherCommentDtoMapper: TeacherCommentDtoMapper,
) {

    @ApiOperation(value = "선생님 정보에 댓글 생성")
    @PostMapping("/{teacherId}")
    fun create(
        @PathVariable("teacherId") teacherId: UUID,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.create(teacherId, commentCommand)
    }

    @ApiOperation(value = "선생님 정보에 대댓글 생성")
    @PostMapping("/{teacherId}/{commentId}")
    fun create(
        @PathVariable("teacherId") teacherId: UUID,
        @PathVariable("commentId") commendId: UUID,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.create(teacherId, commendId, commentCommand)
    }

    @ApiOperation(value = "선생님 정보에 댓글 전체 조회")
    @GetMapping
    fun getComments(): CommonResponse<List<TeacherCommentResponse.Main>> {
        val response = teacherCommentService.getComments()
        return CommonResponse.success(response)
    }

    @ApiOperation(value = "선생님 정보에 댓글 수정")
    @PutMapping("/{commentId}")
    fun update(
        @PathVariable("commentId") commentId: UUID,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.update(commentId, commentCommand)
    }

    @ApiOperation(value = "선생님 정보에 댓글 삭제")
    @DeleteMapping("/{commentId}")
    fun delete(@PathVariable("commentId") commentId: UUID) {
        teacherCommentService.delete(commentId)
    }
}