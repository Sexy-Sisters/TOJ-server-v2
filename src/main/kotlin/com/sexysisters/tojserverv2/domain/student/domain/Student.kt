package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.user.User
import javax.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    @Embedded
    val grade: Grade,
    @Embedded
    val classroom: Classroom,
    @Embedded
    val number: Number,
    @Embedded
    val age: Age,
) : BaseTimeEntity() {
    @Enumerated(EnumType.STRING)
    var status: Status = Status.INDEPENDENT

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    var school: School? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToMany(mappedBy = "applicant", cascade = [CascadeType.ALL])
    val approves = mutableSetOf<Approve>()

    @OneToMany(mappedBy = "acceptor", cascade = [CascadeType.ALL])
    val acceptors = mutableSetOf<Approve>()

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

fun Student.makeRelation(user: User) {
    this.user = user
    user.student = this
}

fun Student.makeRelation(school: School) {
    this.school = school
    school.students.add(this)
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