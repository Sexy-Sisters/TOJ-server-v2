package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Code(
    @field:NotNull
    @Column(name = "code", nullable = false, unique = true)
    val value: String
) {
    init {
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.length != 7) throw SchoolException.SchoolNotValid()
    }
}
