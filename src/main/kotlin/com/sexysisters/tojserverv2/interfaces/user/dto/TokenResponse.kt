package com.sexysisters.tojserverv2.interfaces.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
)