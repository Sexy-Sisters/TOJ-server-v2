package com.sexysisters.tojserverv2.common.util.api.oauth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
)