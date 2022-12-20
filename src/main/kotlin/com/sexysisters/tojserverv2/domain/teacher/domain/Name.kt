package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Name(
    @field:NotNull
    @Column(name = "name", unique = true)
    val value: String
) {
    init {
        val MIN_LENGHT = 1
        val MAX_LENGHT = 100
        if (value.length !in MIN_LENGHT..MAX_LENGHT) throw TeacherException.TeacherNotValid()
    }
}