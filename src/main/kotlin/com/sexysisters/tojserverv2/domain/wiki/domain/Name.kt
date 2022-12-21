package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Name(
    @field:NotNull
    @Column(name = "name")
    val value: String
) {
    init {
        val MIN_LENGTH = 4
        val MAX_LENGTH = 21

        if (value.isBlank()) throw WikiException.WikiNotValid()
        if (value.length !in MIN_LENGTH..MAX_LENGTH) throw WikiException.WikiNotValid()
    }
}