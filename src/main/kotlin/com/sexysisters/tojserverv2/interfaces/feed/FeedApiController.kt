package com.sexysisters.tojserverv2.interfaces.feed

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.feed.FeedCommand
import com.sexysisters.tojserverv2.domain.feed.service.FeedService
import com.sexysisters.tojserverv2.interfaces.feed.dto.FeedDtoMapper
import com.sexysisters.tojserverv2.interfaces.feed.dto.FeedRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["피드 관련 API"])
@RestController
@RequestMapping("/api/v2/feed")
class FeedApiController(
    private val feedService: FeedService,
    private val feedDtoMapper: FeedDtoMapper,
) {

    @ApiOperation(value = "피드 게시")
    @PostMapping
    fun postFeed(@RequestBody @Valid request: FeedRequest.Create): CommonResponse<Long> {
        val command = feedDtoMapper.of(request)
        val feedId = feedService.createFeed(command)
        return CommonResponse.success(feedId)
    }

    @ApiOperation(value = "피드 글 수정")
    @PutMapping
    fun updateFeed(@RequestBody @Valid request: FeedRequest.Update) {
        val command: FeedCommand.Update = feedDtoMapper.of(request)
        feedService.updateFeed(command)
    }
}