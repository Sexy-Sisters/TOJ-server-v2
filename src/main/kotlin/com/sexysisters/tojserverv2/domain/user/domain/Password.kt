package com.sexysisters.tojserverv2.domain.user.domain

import com.sexysisters.tojserverv2.domain.user.exception.UserException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Password(
    @field:NotNull
    @Column(name = "password")
    val value: String
) {
    init {
        val MAX_LENGTH = 20
        if (value.isBlank()) throw UserException.UserNotValid()
        if (value.length > MAX_LENGTH) throw UserException.UserNotValid()
    }
}