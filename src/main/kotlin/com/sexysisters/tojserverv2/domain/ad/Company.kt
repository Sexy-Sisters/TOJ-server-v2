package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.apache.commons.lang3.StringUtils
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Company(
    @Column
    val name: String,
    @Column
    val advertiser: String,
) {
    init {
        if (StringUtils.isEmpty(name)) throw AdException.AdNotValid()
        if (StringUtils.isEmpty(advertiser)) throw AdException.AdNotValid()
    }
}