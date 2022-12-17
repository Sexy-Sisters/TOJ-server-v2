package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Nickname private constructor(
    @Column(name = "nickname")
    val value: String
) {
    companion object {
        private val REGEX = Regex("^{1,50}$")

        fun of(value: String): Nickname {
            validate(value)
            return Nickname(value)
        }

        private fun validate(value: String) {
            if (REGEX.matches(value)) throw TeacherException.TeacherNotValid()
        }
    }
}