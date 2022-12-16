package com.sexysisters.tojserverv2.interfaces.ad

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import com.sexysisters.tojserverv2.domain.ad.service.AdService
import com.sexysisters.tojserverv2.infrastructure.ad.Sort
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdDtoMapper
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdRequest
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/ad")
class AdApiController(
    private val adService: AdService,
    private val adDtoMapper: AdDtoMapper,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun openAd(@RequestBody @Valid request: AdRequest.Create) {
        val adCommand = adDtoMapper.of(request)
        adService.openAd(adCommand)
    }

    @DeleteMapping("/{id}")
    fun deleteAd(@PathVariable id: Long) {
        adService.deleteAd(id)
    }

    @GetMapping
    fun getAdList(
        @RequestParam(name = "status", required = false) status: Status?,
        @RequestParam(name = "adKind", required = false) adKind: AdKind?,
        @RequestParam(name = "sort", required = false) sort: Sort?,
    ): CommonResponse<List<AdResponse.Main>> {
        val adInfo = adService.getAdList(status, adKind, sort)
        val response = adInfo.map { adDtoMapper.of(it) }
        return CommonResponse.success(response)
    }
}