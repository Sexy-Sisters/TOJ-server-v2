package com.sexysisters.tojserverv2.interfaces.approve

import com.sexysisters.tojserverv2.domain.approve.service.ApproveService
import com.sexysisters.tojserverv2.interfaces.approve.dto.ApproveDtoMapper
import com.sexysisters.tojserverv2.interfaces.approve.dto.ApproveRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["학생 관련 API"])
@RestController
@RequestMapping("/api/v2/approve")
class ApproveApiController(
    private val approveService: ApproveService,
    private val approveDtoMapper: ApproveDtoMapper
) {

    @ApiOperation("참가 신청한 학생 리스트에서 이미 참가된 학생이 요청을 수락할 수 있다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun approve(@RequestBody @Valid request: ApproveRequest.Approve) {
        val command = approveDtoMapper.of(request)
        approveService.approve(command)
    }

    @ApiOperation("요청 수락 취소")
    @DeleteMapping
    fun cancel(@RequestBody @Valid request: ApproveRequest.Cancel) {
        val command = approveDtoMapper.of(request)
        approveService.cancel(command)
    }
}