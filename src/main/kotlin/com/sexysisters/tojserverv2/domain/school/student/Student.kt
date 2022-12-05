package com.sexysisters.tojserverv2.domain.school.student

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.user.User
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tbl_student")
class Student(
    val grade: Int,
    val classroom: Int,
    val number: Int,
    val age: Int,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.NONE,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseTimeEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    var school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

enum class Status(
    val description: String,
) {
    NONE("무소속"),
    WAITING("대기 중"),
    ENGAGED("소속"),
}

fun Student.updateStatus(status: Status): String {
    this.status = status
    return status.description
}