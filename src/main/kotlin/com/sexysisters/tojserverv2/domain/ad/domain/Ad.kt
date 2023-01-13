package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.BaseEntity
import com.sexysisters.tojserverv2.domain.ad.domain.custinfo.CostInfo
import javax.persistence.*

@Entity
@Table(name = "tbl_ad")
class Ad(
    @Enumerated(value = EnumType.STRING)
    val adKind: AdKind,

    @Embedded
    val image: Image,

    @Embedded
    val link: Link,

    @Embedded
    val expirationDate: ExpirationDate,

    /*
    @author Lee Kyu-jin
    @to Nam Se-won
    TODO :: These normal embedded type columns should be changed to wrapper type using embedded type
    */
    @Embedded
    val costInfo: CostInfo,

    @Embedded
    val company: Company,
) : BaseEntity() {

    @Enumerated(value = EnumType.STRING)
    var status: Status = Status.OPEN
}

fun Ad.close() { this.status = Status.CLOSE }

fun Ad.open() { this.status = Status.OPEN }

enum class Status { OPEN, CLOSE, }