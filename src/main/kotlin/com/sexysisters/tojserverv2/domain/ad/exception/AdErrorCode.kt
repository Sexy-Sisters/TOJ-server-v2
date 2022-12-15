package com.sexysisters.tojserverv2.domain.ad.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class AdErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    AD_NOT_VALID("Ad domain is not valid"),
    AD_NOT_FOUND("Ad does not exists"),
}