package com.sexysisters.tojserverv2.infrastructure.student

import com.querydsl.jpa.impl.JPAQueryFactory
import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.student.QStudent.student
import org.springframework.stereotype.Component

@Component
class StudentCustomRepositoryImpl(
    private val query: JPAQueryFactory,
) : StudentCustomRepository {

    override fun checkAlreadyExists(
        school: School,
        grade: Int,
        classroom: Int,
        number: Int
    ): Boolean {
        val student = query
            .selectFrom(student)
            .where(
                student.school.eq(school),
                student.grade.eq(grade),
                student.classroom.eq(classroom),
                student.number.eq(number),
            )
            .fetchFirst()
        return student != null
    }
}