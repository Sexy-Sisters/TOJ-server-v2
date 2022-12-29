package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    @Embedded val grade: Grade,
    @Embedded val classroom: Classroom,
    @Embedded val number: Number,
    @Embedded val age: Age,
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var status: Status = Status.INDEPENDENT
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    var school: School? = null
        private set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null
        private set

    @OneToMany(mappedBy = "applicant", cascade = [CascadeType.ALL])
    val approves = mutableSetOf<Approve>()

    @OneToMany(mappedBy = "acceptor", cascade = [CascadeType.ALL])
    val acceptors = mutableSetOf<Approve>()

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], orphanRemoval = true, mappedBy = "student")
    protected val mutableComments: MutableList<Comment> = mutableListOf()
    val comments: List<Comment> get() = mutableComments.toList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun makeRelation(user: User) {
        this.user = user
        user.student = this
    }

    fun makeRelation(school: School) {
        this.school = school
        school.students.add(this)
    }

    fun independent() {
        status = Status.INDEPENDENT
    }

    fun waiting() {
        status = Status.WAITING
    }

    fun engaged() {
        status = Status.ENGAGED
    }

    fun isAttendSchool() = school != null

    fun gradeValue() = grade.value
    fun classroomValue() = classroom.value
    fun numberValue() = number.value
    fun ageValue() = age.value

    fun writeComment(comment: Comment) {
        mutableComments.add(comment)
    }

    fun isSame(writer: Student): Boolean {
        return this.id == writer.id
    }
}