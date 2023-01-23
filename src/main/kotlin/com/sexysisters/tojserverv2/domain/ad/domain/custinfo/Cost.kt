package com.sexysisters.tojserverv2.domain.ad.domain.custinfo

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Cost(
    @NotNull
    @Column(name = "cost")
    val value: Int
) {
    init {
        if (value < 0) AdException.AdNotValid()
    }
}