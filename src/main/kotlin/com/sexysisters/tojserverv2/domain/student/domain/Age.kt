package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Age(
    @field:NotNull
    @Column(name = "age")
    val value: Int,
) {
    init {
        val MIN = 8
        val MAX = 40
        if (value !in MIN..MAX) throw StudentException.StudentNotValid()
    }
}