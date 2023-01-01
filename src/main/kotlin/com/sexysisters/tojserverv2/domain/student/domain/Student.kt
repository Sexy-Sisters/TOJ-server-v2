package com.sexysisters.tojserverv2.domain.student.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.domain.feed.domain.Feed
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike
import com.sexysisters.tojserverv2.domain.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    user: User,
    school: School,
    @Embedded val grade: Grade,
    @Embedded val classroom: Classroom,
    @Embedded val number: Number,
    @Embedded val age: Age,
) : BaseTimeEntity() {

    init {
        school.students.add(this)
        user.student = this
    }

    @Enumerated(EnumType.STRING)
    var status: Status = Status.INDEPENDENT
        private set

    @OneToOne(
        fetch = FetchType.LAZY,
        mappedBy = "student",
    )
    var user: User = user
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    var school: School = school
        private set

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "applicant",
        cascade = [CascadeType.ALL]
    )
    val approves = mutableSetOf<Approve>()

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "acceptor",
        cascade = [CascadeType.ALL]
    )
    val acceptors = mutableSetOf<Approve>()

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "student",
    )
    private val mutableComments: MutableList<Comment> = mutableListOf()
    val comments: List<Comment> get() = mutableComments.toList()

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "writer",
        cascade = [CascadeType.ALL]
    )
    val feeds = mutableSetOf<Feed>()

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "student",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true
    )
    private val mutableTeacherLikes: MutableList<TeacherLike> = mutableListOf()
    val teacherLikes get(): List<TeacherLike> = mutableTeacherLikes.toList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun independent() {
        status = Status.INDEPENDENT
    }

    fun waiting() {
        status = Status.WAITING
    }

    fun engaged() {
        status = Status.ENGAGED
    }

    fun isAttendSchool() = status != Status.INDEPENDENT

    fun gradeValue() = grade.value
    fun classroomValue() = classroom.value
    fun numberValue() = number.value
    fun ageValue() = age.value

    fun writeComment(comment: Comment) {
        mutableComments.add(comment)
    }

    fun likeTeacher(teacherLike: TeacherLike) {
        mutableTeacherLikes.add(teacherLike)
    }

    fun isSame(writer: Student): Boolean {
        return this.id == writer.id
    }
}