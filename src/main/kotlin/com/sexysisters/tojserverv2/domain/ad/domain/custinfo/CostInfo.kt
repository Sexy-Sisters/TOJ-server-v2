package com.sexysisters.tojserverv2.domain.ad.domain.custinfo

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class CostInfo(
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    val type: Type,

    @Embedded
    val cost: Cost,
) {
    @Embedded
    val views: Views = Views()

    @Embedded
    var clicks: Clicks = Clicks()
}