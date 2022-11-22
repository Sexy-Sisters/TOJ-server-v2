package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_user")
class User(
    val email: String,
    var password: String,
    var nickname: String,
    var profileImg: String,
    var name: String = "수정해주세요",
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var authority: Authority = Authority.USER

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

enum class Authority {
    ADMIN, USER
}