package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Address(
    @field:NotNull
    @Column(name = "address", unique = true)
    val value: String
) {
    init {
        val MAX_LENGTH = 34
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.length > MAX_LENGTH) throw SchoolException.SchoolNotValid()
    }
}