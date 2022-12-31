package com.sexysisters.tojserverv2.domain.teacher.service

import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.teacher.*
import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.domain.teacher.domain.Content
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeacherCommentServiceImpl(
    private val studentReader: StudentReader,
    private val teacherReader: TeacherReader,
    private val teacherCommentStore: TeacherCommentStore,
    private val teacherCommentReader: TeacherCommentReader,
    private val teacherCommentMapper: TeacherCommentMapper
) : TeacherCommentService {

    override fun create(teacherId: Long, command: TeacherCommentCommand.Main) {
        val student: Student = studentReader.getCurrentStudent()
        val teacher: Teacher = teacherReader.getTeacher(teacherId)
        val initComment: Comment = toEntity(command, student, teacher)
        teacherCommentStore.store(initComment)
    }

    override fun create(teacherId: Long, commentId: Long, command: TeacherCommentCommand.Main) {
        val student: Student = studentReader.getCurrentStudent()
        val teacher: Teacher = teacherReader.getTeacher(teacherId)
        val parent: Comment = teacherCommentReader.getComment(commentId)
        val initComment: Comment = toEntity(command, student, teacher)
        initComment.writeChildComment(parent)
        teacherCommentStore.store(initComment)
    }

    private fun toEntity(
        command: TeacherCommentCommand.Main,
        student: Student,
        teacher: Teacher
    ) = Comment(
        content = Content(command.content),
        student = student,
        teacher = teacher,
        isAnonymous = command.anonymous
    )

    @Transactional(readOnly = true)
    override fun getComments(): List<TeacherCommentResponse.Main> {
        val comments: List<Comment> = teacherCommentReader.getComments()
        return comments.map { teacherCommentMapper.of(it) }
    }

    override fun update(commentId: Long, command: TeacherCommentCommand.Main) {
        val student: Student = studentReader.getCurrentStudent()
        val comment: Comment = teacherCommentReader.getComment(commentId)
        checkIdentity(student, comment.student)
        comment.update(Content(command.content), command.anonymous)
    }

    override fun delete(commentId: Long) {
        val student: Student = studentReader.getCurrentStudent()
        val comment: Comment = teacherCommentReader.getComment(commentId)
        checkIdentity(student, comment.student)
        teacherCommentStore.delete(comment)
    }

    private fun checkIdentity(student: Student, writer: Student) {
        if (!student.isSame(writer)) StudentException.DifferentStudent()
    }
}