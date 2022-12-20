package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Belong(
    @field:NotNull
    @Column(name = "belong", nullable = false)
    val value: String
) {
    init {
        val BELONG_FORMAT = "교육청"
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (!value.endsWith(BELONG_FORMAT)) throw SchoolException.SchoolNotValid()
    }
}