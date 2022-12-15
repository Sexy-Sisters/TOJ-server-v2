package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class CostInfo(
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    val type: Type,

    @Column(nullable = false)
    val cost: Int,

    @Column(nullable = false)
    var views: Int = 0,

    @Column(nullable = false)
    var clicks: Int = 0,
) {
    init {
        if (cost < 0) throw AdException.AdNotValid()
    }
}

fun CostInfo.view() { views++ }

enum class AdKind(
    val description: String,
) {
    DISPLAY("디스플레이 광고"),
    BANNER("배너(띠) 광고"),
}

enum class Type(
    val description: String,
) {
    IMP("노출당 비용"),
    CPC("클릭당 비용"),
    CMP("1,000번 노출당 비용"),
}