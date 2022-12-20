package com.sexysisters.tojserverv2.infrastructure.teacher

import com.querydsl.jpa.impl.JPAQueryFactory
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.teacher.domain.QTeacher.teacher
import org.springframework.stereotype.Component

@Component
class TeacherCustomRepositoryImpl(
    private val query: JPAQueryFactory
) : TeacherCustomRepository {
    override fun search(schoolCode: String): List<Teacher> {
        return query
            .selectFrom(teacher)
            .where(
                teacher.school.code.value.eq(schoolCode),
            )
            .fetch()
    }
}