package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class PasswordMismatchException : BaseException(UserErrorCode.PASSWORD_MISMATCH)