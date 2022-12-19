package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Grade(
    @field:NotNull
    @Column(name = "grade", nullable = false)
    val value: Int,
) {
    init {
        if (value !in 1..6) throw StudentException.StudentNotValid()
    }
}