package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Name(
    @field:NotNull
    @Column(name = "name", nullable = false)
    val value: String
) {
    init {
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.endsWith("학교")) throw SchoolException.SchoolNotValid()
        if (value.length > 21) throw SchoolException.SchoolNotValid()
    }
}
