package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Image(
    @field:NotNull
    @Column(name = "image")
    val value: String,
) {
    init {
        if (value.isBlank()) throw AdException.AdNotValid()
    }
}
