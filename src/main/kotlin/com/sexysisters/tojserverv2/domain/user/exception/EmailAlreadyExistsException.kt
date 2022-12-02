package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.exception.BaseException

class EmailAlreadyExistsException : BaseException(UserErrorCode.EMAIL_ALREADY_EXISTS)