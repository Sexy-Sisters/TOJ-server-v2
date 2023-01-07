package com.sexysisters.tojserverv2.interfaces.ad

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import com.sexysisters.tojserverv2.domain.ad.service.AdService
import com.sexysisters.tojserverv2.infrastructure.ad.Sort
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdDtoMapper
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdRequest
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["광고 관련 API"])
@RestController
@RequestMapping("/api/v2/ad")
class AdApiController(
    private val adService: AdService,
    private val adDtoMapper: AdDtoMapper,
) {

    @ApiOperation("광고 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun openAd(@RequestBody @Valid request: AdRequest.Create) {
        val adCommand = adDtoMapper.of(request)
        adService.openAd(adCommand)
    }

    @ApiOperation("실수로 잘못 생성한 광고 삭제")
    @DeleteMapping("/{id}")
    fun deleteAd(@PathVariable id: String) {
        adService.deleteAd(id)
    }

    @ApiOperation("여러 조건으로 광고 리스트 조회")
    @GetMapping
    fun getAdList(
        @RequestParam(defaultValue = "OPEN") status: Status,
        @RequestParam adKind: AdKind?,
        @RequestParam(defaultValue = "VIEWS") sort: Sort,
    ): CommonResponse<List<AdResponse.Main>> {
        val adInfo = adService.getAdList(status, adKind, sort)
        val response = adInfo.map { adDtoMapper.of(it) }
        return CommonResponse.success(response)
    }
}