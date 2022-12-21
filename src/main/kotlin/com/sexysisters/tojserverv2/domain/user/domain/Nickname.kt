package com.sexysisters.tojserverv2.domain.user.domain

import com.sexysisters.tojserverv2.domain.user.exception.UserException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Nickname(
    @field:NotNull
    @Column(name = "nickname", unique = true)
    val value: String,
) {
    init {
        val MAX_LENGTH = 21
        if (value.isBlank()) throw UserException.UserNotValid()
        if (value.length > MAX_LENGTH) throw UserException.UserNotValid()
    }
}