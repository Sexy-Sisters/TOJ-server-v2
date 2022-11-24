package com.sexysisters.tojserverv2.domain.user.exception

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class UserErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),
    EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
    NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다."),
    PASSWORD_MISMATCH("The password is wrong"),
}