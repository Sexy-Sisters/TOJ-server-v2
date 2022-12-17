package com.sexysisters.tojserverv2.domain.student

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.teacher.Teacher
import com.sexysisters.tojserverv2.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    val grade: Int,
    val classroom: Int,
    val number: Int,
    val age: Int,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.INDEPENDENT,
) : BaseTimeEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    var school: School? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToMany(mappedBy = "applicant", cascade = arrayOf(CascadeType.ALL))
    val approves = mutableListOf<Approve>()

    @OneToMany(mappedBy = "acceptor", cascade = arrayOf(CascadeType.ALL))
    val acceptorList = mutableListOf<Approve>()

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

enum class Status(
    val description: String,
) {
    INDEPENDENT("무소속"),
    WAITING("대기 중"),
    ENGAGED("소속"),
}

fun Student.makeRelation(user: User) {
    this.user = user
    user.student = this
}

fun Student.independent() {
    status = Status.INDEPENDENT
}

fun Student.waiting() {
    status = Status.WAITING
}

fun Student.engaged() {
    status = Status.ENGAGED
}