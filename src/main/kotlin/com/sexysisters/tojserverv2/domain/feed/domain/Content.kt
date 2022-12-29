package com.sexysisters.tojserverv2.domain.feed.domain

import com.sexysisters.tojserverv2.domain.user.exception.UserException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Content(
    @field:NotNull
    @Column(name = "content")
    val value: String,
) {
    init {
        if (value.isBlank()) throw UserException.UserNotValid()
    }
}