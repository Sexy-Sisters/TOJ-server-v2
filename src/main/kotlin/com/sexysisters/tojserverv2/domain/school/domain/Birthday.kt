package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Birthday(
    @field:NotNull
    @Column(name = "code", nullable = false)
    val value: LocalDate
) {
    init {
        val year = value.year
        if (year < 1882) throw SchoolException.SchoolNotValid()
    }
}
