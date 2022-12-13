package com.sexysisters.tojserverv2.interfaces.wiki

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.domain.wiki.service.WikiService
import com.sexysisters.tojserverv2.interfaces.wiki.dto.WikiDtoMapper
import com.sexysisters.tojserverv2.interfaces.wiki.dto.WikiResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["School Wiki 관련 API"])
@RestController
@RequestMapping("/api/v2/wiki")
class WikiApiController(
    private val wikiService: WikiService,
    private val wikiDtoMapper: WikiDtoMapper,
) {

    @ApiOperation("학교 조회 화면에 등잘할 스쿨위키 단건 조회")
    @GetMapping
    fun getSchoolWiki(@RequestParam("schoolCode") schoolCode: String): CommonResponse<WikiResponse.Main> {
        val wikiInfo = wikiService.getSchoolWiki(schoolCode)
        val response: WikiResponse.Main = wikiDtoMapper.of(wikiInfo)
        return CommonResponse.success(response)
    }
}