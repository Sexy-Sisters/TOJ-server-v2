package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.BaseEntity
import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tbl_ad")
class Ad(
    @Enumerated(value = EnumType.STRING)
    val adKind: AdKind,
    val image: String,
    val link: String,
    val expirationDate: LocalDate,

    @Embedded
    val costInfo: CostInfo,

    @Embedded
    val company: Company,
) : BaseEntity() {

    init {
        if (image.isBlank()) throw AdException.AdNotValid()
        if (link.isBlank()) throw AdException.AdNotValid()
    }

    @Enumerated(value = EnumType.STRING)
    var status: Status = Status.OPEN
}

fun Ad.close() { this.status = Status.CLOSE }

fun Ad.open() { this.status = Status.OPEN }

enum class Status { OPEN, CLOSE, }