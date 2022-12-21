package com.sexysisters.tojserverv2.domain.wiki.domain

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Views {
    @field:NotNull
    @Column(name = "views")
    var value: Int = 0
}