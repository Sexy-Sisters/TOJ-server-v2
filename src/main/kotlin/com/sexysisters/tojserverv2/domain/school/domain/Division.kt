package com.sexysisters.tojserverv2.domain.school.domain

enum class Division(
    val description: String,
) {
    ELEMENTARY("초등학교"),
    MIDDLE("중학교"),
    HIGH("고등학교"),
}

fun getDivision(description: String) = when (description) {
    Division.ELEMENTARY.description -> Division.ELEMENTARY
    Division.MIDDLE.description -> Division.MIDDLE
    Division.HIGH.description -> Division.HIGH
    else -> null
}