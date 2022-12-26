package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.domain.School
import javax.persistence.*

@Entity
@Table(name = "tbl_teacher")
class Teacher(
    image: Image,
    name: Name,
    nickname: Nickname,
    bio: Bio,
) : BaseTimeEntity() {

    @Embedded
    var image: Image = image
        private set

    @Embedded
    var name: Name = name
        private set

    @Embedded
    var nickname: Nickname = nickname
        private set

    @Embedded
    var bio: Bio = bio
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_school_id")
    val school: School? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun update(image: Image, name: Name, nickname: Nickname, bio: Bio) {
        this.image = image
        this.name = name
        this.nickname = nickname
        this.bio = bio
    }
}