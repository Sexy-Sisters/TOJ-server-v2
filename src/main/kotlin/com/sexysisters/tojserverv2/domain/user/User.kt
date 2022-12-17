package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import com.sexysisters.tojserverv2.domain.user.type.Authority
import javax.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    val email: String,
    var password: String,
    var nickname: String,
    var profileImg: String,
    var name: String,
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var authority: Authority = Authority.USER

    @OneToOne(mappedBy = "user")
    var student: Student? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (email.isBlank()) throw UserException.UserNotValid()
        if (password.isBlank()) throw UserException.UserNotValid()
        if (nickname.isBlank()) throw UserException.UserNotValid()
        if (profileImg.isBlank()) throw UserException.UserNotValid()
        if (name.isBlank()) throw UserException.UserNotValid()
    }
}

fun User.updateInfo(
    nickname: String,
    name: String,
): User {
    this.nickname = nickname
    this.name = name
    return this
}

fun User.updateProfileImg(profileImg: String) {
    this.profileImg = profileImg
}

fun User.hasStudent() = student != null