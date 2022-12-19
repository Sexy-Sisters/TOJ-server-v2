package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class HomePageAddress(
    @field:NotNull
    @Column(name = "home_page_address", nullable = false, unique = true)
    val value: String
) {
    init {
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
    }
}
