package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Image private constructor (
    @Column(name = "image")
    val value: String
) {
    companion object {
        fun of(value: String): Image {
            validate(value)
            return Image(value)
        }

        private fun validate(value: String) {
            if (value.isBlank()) throw TeacherException.TeacherNotValid()
        }
    }
}