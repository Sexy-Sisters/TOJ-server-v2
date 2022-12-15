package com.sexysisters.tojserverv2.interfaces.ad

import com.sexysisters.tojserverv2.domain.ad.service.AdService
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdDtoMapper
import com.sexysisters.tojserverv2.interfaces.ad.dto.AdRequest
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
}