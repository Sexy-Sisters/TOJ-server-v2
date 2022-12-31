package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.domain.School
import com.sexysisters.tojserverv2.domain.teacherLike.domain.TeacherLike
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

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "teacher",
    )
    private val mutableComments: MutableList<Comment> = mutableListOf()
    val comments get(): List<Comment> = mutableComments.toList()

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "teacher",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true
    )
    private val mutableTeacherLikes: MutableList<TeacherLike> = mutableListOf()
    val teacherLikes get(): List<TeacherLike> = mutableTeacherLikes.toList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tbl_teacher_id")
    val id: Long = 0L

    fun registeredComment(comment: Comment) {
        mutableComments.add(comment)
    }

    fun addTeacherLike(teacherLike: TeacherLike) {
        mutableTeacherLikes.add(teacherLike)
    }

    fun update(image: Image, name: Name, nickname: Nickname, bio: Bio) {
        this.image = image
        this.name = name
        this.nickname = nickname
        this.bio = bio
    }
}