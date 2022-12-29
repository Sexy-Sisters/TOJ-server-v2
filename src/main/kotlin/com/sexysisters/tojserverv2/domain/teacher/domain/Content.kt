package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.teacher.exception.TeacherCommentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Content(
    @field:NotNull
    @Column(name = "content")
    val value: String
) {
    init {
        val MIN_LENGTH = 1
        val MAX_LENGTH = 1000
        if (value.isBlank()) throw TeacherCommentException.TeacherCommentNotValid()
        if (value.length !in MIN_LENGTH..MAX_LENGTH) throw TeacherCommentException.TeacherCommentNotValid()
    }
}