package com.sexysisters.tojserverv2.domain.teacher

import com.sexysisters.tojserverv2.domain.teacher.domain.*
import org.springframework.stereotype.Component

@Component
class TeacherEntityMapper {
    fun of(command: TeacherCommand.Create) = Teacher(
        Image(command.image),
        Name(command.name),
        Nickname(command.nickname),
        Bio(command.bio),
    )
}