package com.sexysisters.tojserverv2.domain.wiki.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class WikiException {
    class WikiNotValid : BaseException(WikiErrorCode.WIKI_NOT_VALID)
    class WikiNotFound : BaseException(WikiErrorCode.WIKI_NOT_FOUND)
}