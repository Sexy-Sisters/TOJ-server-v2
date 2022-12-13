package com.sexysisters.tojserverv2.domain.wiki.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class WikiErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    WIKI_NOT_VALID("Wiki domain is not valid"),
}