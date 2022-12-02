package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_school")
class School(
    val schoolCode: String,
    val belong: String,
    val name: String,
    val address: String,
    val headcount: Int,
    val birthDay: LocalDate,
    val homePageAddress: String,
    val phoneNumber: String,
    // TODO :: 평점

    @Enumerated(EnumType.STRING)
    val grade: Grade,

    @Enumerated(EnumType.STRING)
    val kind: Kind,
) : BaseTimeEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

enum class Grade {
    ELEMENTRY, MIDDLE, HIGH,
}

enum class Kind {
    SPECIAL_PURPOSE, VOCATIONAL, ORDINARY,
}