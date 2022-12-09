package com.sexysisters.tojserverv2.domain.auth.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class AuthException {
    class PasswordMismatch : BaseException(AuthErrorCode.PASSWORD_MISMATCH)
}