package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Bio(
    @field:NotNull
    @Column(name = "bio")
    val value: String
) {
    init {
        val MIN_LENGTH = 1
        val MAX_LENGTH = 500
        if (value.length !in MIN_LENGTH..MAX_LENGTH) throw TeacherException.TeacherNotValid()
    }
}