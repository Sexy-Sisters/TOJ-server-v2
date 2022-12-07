package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.user.type.Authority
import org.apache.commons.lang3.StringUtils
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

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
        if (StringUtils.isEmpty(email)) throw StudentException.StudentEmptyProperties()
        if (StringUtils.isEmpty(password)) throw StudentException.StudentEmptyProperties()
        if (StringUtils.isEmpty(nickname)) throw StudentException.StudentEmptyProperties()
        if (StringUtils.isEmpty(profileImg)) throw StudentException.StudentEmptyProperties()
        if (StringUtils.isEmpty(name)) throw StudentException.StudentEmptyProperties()
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