package com.sexysisters.tojserverv2.domain.ad.domain.company

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Advertiser(
    @NotNull
    @Column(name = "advertiser")
    val value: String,
) {
    init {
        if (value.isBlank()) AdException.AdNotValid()
    }
}