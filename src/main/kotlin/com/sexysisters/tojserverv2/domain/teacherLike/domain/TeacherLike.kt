package com.sexysisters.tojserverv2.domain.teacherLike.domain

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import javax.persistence.*

@Entity
@Table(name = "tbl_teacher_like")
class TeacherLike(
    teacher: Teacher,
    student: Student
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_teacher_id")
    var teacher = teacher
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_student_id")
    var student = student
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        teacher.addTeacherLike(this)
        student.likeTeacher(this)
    }
}