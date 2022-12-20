package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Phone(
    @field:NotNull
    @Column(name = "phone", unique = true)
    val value: String
) {
    init {
        val MIN_LENGTH = 11
        val MAX_LENTH = 12
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.length !in MIN_LENGTH..MAX_LENTH) throw SchoolException.SchoolNotValid()
    }
}