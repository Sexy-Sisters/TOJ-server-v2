package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class CostInfo(
    @Enumerated(value = EnumType.STRING)
    val costType: CostType,
    val cost: Int,
    val views: Int = 0,
) {
    init {
        if (cost < 0) throw AdException.AdNotValid()
    }
}

enum class AdKind(
    val description: String,
) {
    DISPLAY("디스플레이 광고"),
    BANNER("배너(띠) 광고"),
}

enum class CostType(
    val description: String,
) {
    IMP("노출당 비용"),
    CPC("클릭당 비용"),
    CMP("1,000번 노출당 비용"),
}