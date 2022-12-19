package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Bio private constructor(
    @Column(name = "bio")
    val value: String
) {
    companion object {
        private val REGEX = Regex("^{1,500}$")

        fun of(value: String): Bio {
            validate(value)
            return Bio(value)
        }

        private fun validate(value: String) {
            if (REGEX.matches(value)) throw TeacherException.TeacherNotValid()
        }
    }
}