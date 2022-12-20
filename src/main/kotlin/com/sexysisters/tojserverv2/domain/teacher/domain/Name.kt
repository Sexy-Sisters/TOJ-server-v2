package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Name private constructor (
    @field:NotNull
    @Column(name = "name", unique = true)
    val value: String
) {
    companion object {
        private val REGEX = Regex("^{1,100}$")

        fun of(value: String): Name {
            validate(value)
            return Name(value)
        }

        private fun validate(value: String) {
            if (REGEX.matches(value)) throw TeacherException.TeacherNotValid()
        }
    }
}