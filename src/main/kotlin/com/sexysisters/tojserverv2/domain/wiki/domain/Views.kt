package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Views(
    @field:NotNull
    @Column(name = "views")
    val value: Int = 0
) {
    init {
        if (value < 0) throw WikiException.WikiNotValid()
    }
}