package com.sexysisters.tojserverv2.domain.ad.domain.custinfo

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Clicks {
    @field:NotNull
    @Column(name = "clicks")
    var value: Int = 0
        private set

    fun countClicks() = value++
    init {
        if (value < 0) throw WikiException.WikiNotValid()
    }
}