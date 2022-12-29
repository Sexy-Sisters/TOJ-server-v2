package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment

interface TeacherCommentStore {
    fun store(comment: Comment): Comment
    fun delete(comment: Comment)
}