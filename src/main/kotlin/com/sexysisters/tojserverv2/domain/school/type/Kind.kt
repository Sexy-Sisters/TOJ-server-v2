package com.sexysisters.tojserverv2.domain.school.type

enum class Kind(
    val description: String,
) {
    SPECIAL_PURPOSE("특목고"),
    VOCATIONAL("특성화"),
    ORDINARY("일반계"),
}

fun getKind(description: String): Kind? {
    return when (description) {
        Kind.SPECIAL_PURPOSE.description -> Kind.SPECIAL_PURPOSE
        Kind.VOCATIONAL.description -> Kind.VOCATIONAL
        Kind.ORDINARY.description -> Kind.ORDINARY
        else -> null
    }
}