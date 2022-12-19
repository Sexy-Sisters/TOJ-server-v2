package com.sexysisters.tojserverv2.domain.student.domain

enum class Status(
    val description: String,
) {
    INDEPENDENT("무소속"),
    WAITING("대기 중"),
    ENGAGED("소속"),
}