package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Name(
    @field:NotNull
    @Column(name = "name")
    val value: String
) {
    init {
        val MAX_LENGTH = 21
        val SCHOOL_FORMAT = "학교"

        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (!value.endsWith(SCHOOL_FORMAT)) throw SchoolException.SchoolNotValid()
        if (value.length > MAX_LENGTH) throw SchoolException.SchoolNotValid()
    }
}