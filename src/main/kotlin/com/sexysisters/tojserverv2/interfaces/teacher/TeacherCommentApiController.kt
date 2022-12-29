package com.sexysisters.tojserverv2.interfaces.teacher

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.teacher.service.TeacherCommentService
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentDtoMapper
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentRequest
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["선생님 댓글 관련 API"])
@RestController
@RequestMapping("/api/v2/teacher/comment")
class TeacherCommentApiController(
    private val teacherCommentService: TeacherCommentService,
    private val teacherCommentDtoMapper: TeacherCommentDtoMapper,
) {

    @PostMapping("/{teacherId}")
    fun create(
        @PathVariable("teacherId") teacherId: Long,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.create(teacherId, commentCommand)
    }

    @PostMapping("/{teacherId}/{commentId}")
    fun create(
        @PathVariable("teacherId") teacherId: Long,
        @PathVariable("commentId") commendId: Long,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.create(teacherId, commendId, commentCommand)
    }

    @GetMapping
    fun getComments(): CommonResponse<List<TeacherCommentResponse.Main>> {
        val response = teacherCommentService.getComments()
        return CommonResponse.success(response)
    }

    @PutMapping("/{commentId}")
    fun update(
        @PathVariable("commentId") commentId: Long,
        @RequestBody request: TeacherCommentRequest.Main
    ) {
        val commentCommand = teacherCommentDtoMapper.of(request)
        teacherCommentService.update(commentId, commentCommand)
    }

    @DeleteMapping("/{commentId}")
    fun delete(@PathVariable("commentId") commentId: Long) {
        teacherCommentService.delete(commentId)
    }
}