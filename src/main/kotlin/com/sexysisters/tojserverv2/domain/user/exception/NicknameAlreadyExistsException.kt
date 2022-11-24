package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class NicknameAlreadyExistsException : BaseException(UserErrorCode.NICKNAME_ALREADY_EXISTS)