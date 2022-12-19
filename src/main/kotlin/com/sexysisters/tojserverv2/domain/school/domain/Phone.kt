package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Phone(
    @field:NotNull
    @Column(name = "phone", nullable = false, unique = true)
    val value: String
) {
    init {
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.length >= 12) throw SchoolException.SchoolNotValid()
    }
}