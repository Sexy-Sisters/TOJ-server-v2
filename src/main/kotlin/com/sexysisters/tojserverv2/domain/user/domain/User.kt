package com.sexysisters.tojserverv2.domain.user.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    @Embedded val email: Email,
    @Embedded var password: Password,
    @Embedded var nickname: Nickname,
    @Embedded var image: Image,
    @Embedded var name: Name,
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var authority: Authority = Authority.USER

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    var student: Student? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun emailValue() = email.value
    fun passwordValue() = password.value
    fun nicknameValue() = nickname.value
    fun imageValue() = image.value
    fun nameValue() = name.value
}

fun User.updateInfo(
    nickname: String,
    name: String,
): User {
    this.nickname = Nickname(nickname)
    this.name = Name(name)
    return this
}

fun User.updateProfileImg(image: String) {
    this.image = Image(image)
}

fun User.hasStudent() = student != null