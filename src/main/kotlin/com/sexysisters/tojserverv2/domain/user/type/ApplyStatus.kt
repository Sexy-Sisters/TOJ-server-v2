package com.sexysisters.tojserverv2.domain.user.type

enum class ApplyStatus(
    val description: String,
) {
    NONE("무소속"),
    WAITING("대기 중"),
    ENGAGED("소속"),
}