package com.sexysisters.tojserverv2.domain.school.domain

enum class Kind(
    val description: String,
) {
    SPECIAL_PURPOSE("특목고"),
    VOCATIONAL("특성화고"),
    ORDINARY("일반고"),
}

fun getKind(description: String) = when (description) {
    Kind.SPECIAL_PURPOSE.description -> Kind.SPECIAL_PURPOSE
    Kind.VOCATIONAL.description -> Kind.VOCATIONAL
    Kind.ORDINARY.description -> Kind.ORDINARY
    else -> null
}