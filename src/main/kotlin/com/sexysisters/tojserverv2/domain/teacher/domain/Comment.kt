package com.sexysisters.tojserverv2.domain.teacher.domain

import com.sexysisters.tojserverv2.config.properties.S3Properties
import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_teacher_comment")
class Comment(
    content: Content,
    teacher: Teacher,
    student: Student,
    isAnonymous: Boolean
) : BaseTimeEntity() {
    private val ANONYMOUS_NAME: String = "익명"

    @Embedded
    var content = content
        private set

    @Column(nullable = false)
    var isAnonymous = isAnonymous
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_student_id")
    var student: Student = student
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_teacher_id")
    var teacher: Teacher = teacher
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_parent_id")
    var parent: Comment? = null

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "parent",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
    )
    protected val mutableChildComments: MutableList<Comment> = mutableListOf()
    val childComments: List<Comment> get() = mutableChildComments.toList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L

    init {
        teacher.registeredComment(this)
        student.writeComment(this)
    }

    fun writeChildComment(comment: Comment) {
        this.parent = comment
        parent!!.addChildComment(this)
    }

    private fun addChildComment(comment: Comment) {
        mutableChildComments.add(comment)
    }

    fun update(content: Content, isAnonymous: Boolean) {
        this.content = content
        this.isAnonymous = isAnonymous
    }

    fun getNickname() = if (isAnonymous) ANONYMOUS_NAME
    else student.user!!.nicknameValue()

    fun getProfileImage() =
        if (isAnonymous) S3Properties.defaultProfileImg
        else student.user!!.imageValue()

    fun getContent() = content.value
}