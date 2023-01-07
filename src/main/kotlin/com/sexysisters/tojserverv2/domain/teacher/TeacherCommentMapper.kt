package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.Comment
import com.sexysisters.tojserverv2.interfaces.teacher.dto.TeacherCommentResponse
import org.springframework.stereotype.Component

@Component
class TeacherCommentMapper {
    fun of(comment: Comment) = TeacherCommentResponse.Main(
        profileImg = comment.getProfileImage(),
        nickname = comment.getNickname(),
        content = comment.getContent(),
        createdDate = comment.createdAt
    )
}