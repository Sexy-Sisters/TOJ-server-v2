package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Code(
    @field:NotNull
    @Column(name = "code", unique = true)
    val value: String
) {
    init {
        val CODE_LENGTH = 7
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
        if (value.length != CODE_LENGTH) throw SchoolException.SchoolNotValid()
    }
}