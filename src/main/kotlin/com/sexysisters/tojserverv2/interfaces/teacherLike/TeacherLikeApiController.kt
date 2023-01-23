package com.sexysisters.tojserverv2.interfaces.teacherLike

import com.sexysisters.tojserverv2.domain.teacherLike.service.TeacherLikeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Api(tags = ["선생님 좋아요 관련 API"])
@RestController
@RequestMapping("/api/v2/teacher-like")
class TeacherLikeApiController(
    private val teacherLikeService: TeacherLikeService
) {

    @ApiOperation(value = "좋아요 또는 싫어요 적용")
    @PutMapping("/{teacherId}")
    fun like(@PathVariable("teacherId") teacherId: UUID) {
        teacherLikeService.like(teacherId)
    }
}