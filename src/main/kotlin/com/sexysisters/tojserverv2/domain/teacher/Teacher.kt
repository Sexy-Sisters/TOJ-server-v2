package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.School
import javax.persistence.*

@Entity
@Table(name = "tbl_teacher")
class Teacher (
    val image: String,
    val name: String,
    val nickname: String,
    val bio: String,
) : BaseTimeEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_school_id")
    val school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}