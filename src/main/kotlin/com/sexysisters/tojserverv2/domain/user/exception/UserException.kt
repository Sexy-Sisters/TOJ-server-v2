package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class UserException {
    class EmailAlreadyExists : BaseException(UserErrorCode.EMAIL_ALREADY_EXISTS)
    class NicknameAlreadyExists : BaseException(UserErrorCode.NICKNAME_ALREADY_EXISTS)
    class UserNotFound : BaseException(UserErrorCode.USER_NOT_FOUND)
    class UserNotValid : BaseException(UserErrorCode.USER_NOT_VALID)
}