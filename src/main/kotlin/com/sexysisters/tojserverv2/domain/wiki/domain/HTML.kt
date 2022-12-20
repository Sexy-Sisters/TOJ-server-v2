package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.validation.constraints.NotNull

class HTML(
    @field:NotNull
    @Column(name = "html", length = 100000)
    var value: String
) {
    init {
        if (value.isBlank()) throw WikiException.WikiNotValid()
    }
}