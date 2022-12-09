package com.sexysisters.tojserverv2.domain.auth.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class AuthErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    PASSWORD_MISMATCH("The password is wrong"),
}