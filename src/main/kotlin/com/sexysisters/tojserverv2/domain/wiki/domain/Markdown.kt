package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.config.properties.WikiProperties
import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Markdown(
    @field:NotNull
    @Column(name = "markdown", length = 100000)
    val value: String = WikiProperties.EMPTY
) {
    init {
        if (value.isBlank()) throw WikiException.WikiNotValid()
    }
}