package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Classroom(
    @field:NotNull
    @Column(name = "classroom")
    val value: Int,
) {
    init {
        val MIN = 1
        val MAX = 20
        if (value !in MIN..MAX) throw StudentException.StudentNotValid()
    }
}