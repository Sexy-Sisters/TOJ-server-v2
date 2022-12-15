package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Company(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val advertiser: String,
) {
    init {
        if (name.isBlank()) throw AdException.AdNotValid()
        if (advertiser.isBlank()) throw AdException.AdNotValid()
    }
}