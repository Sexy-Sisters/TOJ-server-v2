package com.sexysisters.tojserverv2.domain.user.type

enum class ApplyStatus(
    val description: String,
) {
    NONE("None"),
    WAITING("대기 중"),
    ENGAGED("참가 중"),
}