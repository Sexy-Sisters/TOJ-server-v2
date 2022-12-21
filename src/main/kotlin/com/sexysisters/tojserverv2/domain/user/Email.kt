package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.user.exception.UserException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Email(
    @field:NotNull
    @Column(name = "email")
    private val value: String,
) {
    init {
        val EMAIL_FORMAT = "@"
        if (value.isBlank()) throw UserException.UserNotValid()
        if (!value.contains(EMAIL_FORMAT)) throw UserException.UserNotValid()
    }
}