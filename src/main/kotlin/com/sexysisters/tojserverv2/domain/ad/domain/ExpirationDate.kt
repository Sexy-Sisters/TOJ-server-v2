package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ExpirationDate(
    @NotNull
    @Column(name = "expiration_date")
    val value: LocalDate
) {
    init {
        val now = LocalDate.now()
        if (value.isBefore(now)) AdException.AdNotValid()
    }
}