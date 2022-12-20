package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherException
import org.hibernate.validator.constraints.URL
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Image private constructor (
    @URL
    @field:NotNull
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