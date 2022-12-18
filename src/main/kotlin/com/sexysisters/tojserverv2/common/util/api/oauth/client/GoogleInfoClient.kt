package com.sexysisters.tojserverv2.common.util.api.oauth.client

import com.sexysisters.tojserverv2.common.util.api.oauth.dto.OAuthInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "GoogleInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleInfoClient {

    @GetMapping("?alt=json&access_token={TOKEN}")
    fun googleInfo(@PathVariable("TOKEN") accessToken: String): OAuthInfoResponse
}