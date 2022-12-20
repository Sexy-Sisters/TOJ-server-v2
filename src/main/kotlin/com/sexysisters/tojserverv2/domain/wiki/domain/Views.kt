package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.Column
import javax.validation.constraints.NotNull

class Views(
    @field:NotNull
    @Column(name = "views", nullable = false)
    var value: Int
) {
    init {
        if (value != 0) throw WikiException.WikiNotValid()
    }
}