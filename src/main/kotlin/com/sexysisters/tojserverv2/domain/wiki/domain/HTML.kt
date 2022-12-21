package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.config.properties.WikiProperties
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class HTML {
    @field:NotNull
    @Column(name = "html", length = 100000)
    var value: String = WikiProperties.EMPTY
}