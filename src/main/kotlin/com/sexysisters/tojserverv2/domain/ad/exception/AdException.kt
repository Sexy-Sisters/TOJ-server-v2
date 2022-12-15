package com.sexysisters.tojserverv2.domain.ad.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class AdException {
    class AdNotValid : BaseException(AdErrorCode.AD_NOT_VALID)
    class AdNotFound : BaseException(AdErrorCode.AD_NOT_FOUND)
}