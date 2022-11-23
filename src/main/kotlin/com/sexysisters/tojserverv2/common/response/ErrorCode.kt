package com.sexysisters.tojserverv2.common.response

enum class ErrorCode(
    val errorMsg: String,
) {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    EXPIRED_TOKEN("Token was expired"),
    INVALID_TOKEN("This token is not valid"),
    ALREADY_LOGOUT("Already logout"),

    OTHER_SERVER_BAD_REQUEST("Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED("Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN("Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN("Other server expired token"),
    
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),
    EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
    NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다."),
}