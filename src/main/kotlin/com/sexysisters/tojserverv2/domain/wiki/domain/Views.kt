package com.sexysisters.tojserverv2.domain.wiki.domain

import javax.persistence.Column
import javax.validation.constraints.NotNull

class Views {
    @field:NotNull
    @Column(name = "views")
    var value: Int = 0
}