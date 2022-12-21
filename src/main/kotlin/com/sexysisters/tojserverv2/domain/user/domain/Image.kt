package com.sexysisters.tojserverv2.domain.user.domain

import com.sexysisters.tojserverv2.domain.user.exception.UserException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Image(
    @field:NotNull
    @Column(name = "image")
    val value: String,
) {
    init {
        if (value.isBlank()) throw UserException.UserNotValid()
    }
}