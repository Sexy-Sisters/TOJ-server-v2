package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.validation.constraints.NotNull

class Name(
    @field:NotNull
    @Column(name = "name")
    val value: String
) {
    init {
        if (value.isBlank()) throw WikiException.WikiNotValid()
        if (value.length !in 2..5) throw WikiException.WikiNotValid()
    }
}