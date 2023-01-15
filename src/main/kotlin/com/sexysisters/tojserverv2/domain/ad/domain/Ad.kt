package com.sexysisters.tojserverv2.domain.ad.domain

import com.sexysisters.tojserverv2.domain.BaseEntity
import com.sexysisters.tojserverv2.domain.ad.domain.company.Company
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

    // @author Lee Kyu-jin
    // TODO :: 래핑 타입과 일반 임베디드 타입의 차이를 두기 위해서 래핑 타입을 위한 어노테이션을 만들 필요가 있어 보임
    @Embedded
    val expirationDate: ExpirationDate,

    // 일반 임베디드 타입
    @Embedded
    val costInfo: CostInfo,

    // 일반 임베디드 타입
    @Embedded
    val company: Company,
) : BaseEntity() {

    @Enumerated(value = EnumType.STRING)
    var status: Status = Status.OPEN
}

fun Ad.close() { this.status = Status.CLOSE }

fun Ad.open() { this.status = Status.OPEN }

enum class Status { OPEN, CLOSE, }