package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Number(
    @field:NotNull
    @Column(name = "number")
    val value: Int,
) {
    init {
        val MIN = 1
        val MAX = 100
        if (value !in MIN..MAX) throw StudentException.StudentNotValid()
    }
}