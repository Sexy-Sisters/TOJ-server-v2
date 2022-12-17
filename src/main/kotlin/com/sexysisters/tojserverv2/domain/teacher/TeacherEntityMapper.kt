package com.sexysisters.tojserverv2.domain.teacher

import org.springframework.stereotype.Component

@Component
class TeacherEntityMapper {
    fun of(command: TeacherCommand.Create) = Teacher(
        Image.of(command.image),
        Name.of(command.name),
        Nickname.of(command.nickname),
        Bio.of(command.bio),
    )
}