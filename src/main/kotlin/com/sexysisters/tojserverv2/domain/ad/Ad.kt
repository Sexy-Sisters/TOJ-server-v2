package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.apache.commons.lang3.StringUtils
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tbl_ad")
class Ad(
    @Enumerated(value = EnumType.STRING)
    val adKind: AdKind,
    val image: String,
    val link: String,
    val experationDate: LocalDateTime,

    @Embedded
    val costInfo: CostInfo,

    @Embedded
    val company: Company,
) : BaseTimeEntity() {

    @Enumerated(value = EnumType.STRING)
    var status: Status = Status.OPEN

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (StringUtils.isEmpty(image)) throw AdException.AdNotValid()
        if (StringUtils.isEmpty(link)) throw AdException.AdNotValid()
    }
}

fun Ad.close() { this.status = Status.CLOSE }

fun Ad.open() { this.status = Status.OPEN }

enum class Status { OPEN, CLOSE, }