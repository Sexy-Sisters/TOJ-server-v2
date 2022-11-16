package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import org.springframework.security.authorization.AuthorityAuthorizationDecision
import javax.persistence.*

@Entity
@Table(name = "tbl_user")
class User (
    var name: String,
    var nickname: String,
    val email: String,
    var password: String,
    var profileImg: String,

    @Enumerated(EnumType.STRING)
    var authority: Authority,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): BaseTimeEntity()

enum class Authority {
    ADMIN, USER
}