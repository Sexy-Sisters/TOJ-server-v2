package com.sexysisters.tojserverv2.domain.user.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    @Embedded val email: Email,
    password: Password,
    nickname: Nickname,
    image: Image,
    name: Name,
) : BaseTimeEntity() {

    @Embedded
    var password: Password = password
        private set

    @Embedded
    var nickname: Nickname = nickname
        private set

    @Embedded
    var image: Image = image
        private set

    @Embedded
    var name: Name = name
        private set

    @Enumerated(EnumType.STRING)
    var authority = Authority.USER

    @OneToOne(
        fetch = FetchType.LAZY,
        mappedBy = "user",
        cascade = [CascadeType.ALL],
    )
    var student: Student? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun updateInfo(
        nickname: Nickname,
        name: Name,
    ): User {
        this.nickname = nickname
        this.name = name
        return this
    }

    fun updateProfileImg(image: Image) {
        this.image = image
    }

    fun hasStudent() = student != null

    fun emailValue() = email.value
    fun passwordValue() = password.value
    fun nicknameValue() = nickname.value
    fun imageValue() = image.value
    fun nameValue() = name.value
}