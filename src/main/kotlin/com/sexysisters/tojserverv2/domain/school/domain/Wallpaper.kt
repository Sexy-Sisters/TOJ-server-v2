package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.config.properties.SchoolProperties
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import org.hibernate.validator.constraints.URL
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Wallpaper(
    @URL
    @NotNull
    @Column(name = "wallpaper")
    val value: String = SchoolProperties.DEFAULT_WALLPAPER
) {
    init {
        if (value.isBlank()) throw SchoolException.SchoolNotValid()
    }
}