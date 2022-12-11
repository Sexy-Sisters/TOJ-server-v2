package com.sexysisters.tojserverv2.interfaces.approve

import com.sexysisters.tojserverv2.domain.approve.service.ApproveService
import com.sexysisters.tojserverv2.interfaces.approve.dto.ApproveDtoMapper
import com.sexysisters.tojserverv2.interfaces.approve.dto.ApproveRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/approve")
class ApproveApiController(
    private val approveService: ApproveService,
    private val approveDtoMapper: ApproveDtoMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun approve(@RequestBody @Valid request: ApproveRequest.Approve) {
        val command = approveDtoMapper.of(request)
        approveService.approve(command)
    }

    @DeleteMapping
    fun cancel(@RequestBody @Valid request: ApproveRequest.Cancel) {
        val command = approveDtoMapper.of(request)
        approveService.cancel(command)
    }
}