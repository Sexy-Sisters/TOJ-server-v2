package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.domain.School
import javax.persistence.*

@Entity
@Table(name = "tbl_teacher")
class Teacher(
    @Embedded
    var image: Image,
    @Embedded
    var name: Name,
    @Embedded
    var nickname: Nickname,
    @Embedded
    var bio: Bio,
) : BaseTimeEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_school_id")
    val school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun update(image: String, name: String, nickname: String, bio: String) {
        this.image = Image(image)
        this.name = Name(name)
        this.nickname = Nickname(nickname)
        this.bio = Bio(bio)
    }
}