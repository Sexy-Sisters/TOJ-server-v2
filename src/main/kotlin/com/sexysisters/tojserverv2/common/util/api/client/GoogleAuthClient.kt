package com.sexysisters.tojserverv2.common.util.api.client

import com.sexysisters.tojserverv2.common.util.api.dto.GoogleCodeRequest
import com.sexysisters.tojserverv2.common.util.api.dto.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
interface GoogleAuthClient {

    @PostMapping
    fun googleAuth(request: GoogleCodeRequest): TokenResponse
}