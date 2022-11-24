package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class UserNotFoundException : BaseException(UserErrorCode.USER_NOT_FOUND)