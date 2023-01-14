package com.sexysisters.tojserverv2.domain.ad.domain.company

import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
data class Company(
    @Embedded
    val name: Name,

    @Embedded
    val advertiser: Advertiser,
)