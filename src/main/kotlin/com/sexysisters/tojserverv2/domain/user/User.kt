package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.student.Student
import com.sexysisters.tojserverv2.domain.user.type.ApplyStatus
import com.sexysisters.tojserverv2.domain.user.type.Authority
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

    @Enumerated(EnumType.STRING)
    var applyStatus: ApplyStatus = ApplyStatus.NONE

    @OneToOne(mappedBy = "user")
    var student: Student? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

fun User.setRelation(school: School) {
    this.school = school
    school.studentList.add(this)
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

fun User.setNone(): String {
    this.applyStatus = ApplyStatus.NONE
    return ApplyStatus.NONE.description
}

fun User.setWaiting(): String {
    this.applyStatus = ApplyStatus.WAITING
    return ApplyStatus.WAITING.description
}

fun User.setEngaged(): String {
    this.applyStatus = ApplyStatus.ENGAGED
    return ApplyStatus.ENGAGED.description
}