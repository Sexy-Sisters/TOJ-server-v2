package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.wiki.Wiki
import javax.persistence.*

@Entity
@Table(name = "tbl_school")
class School(
    @Embedded
    val code: Code,
    @Embedded
    val belong: Belong,
    @Embedded
    val name: Name,
    @Embedded
    val address: Address,
    @Embedded
    val birthDay: Birthday,
    @Embedded
    val homePageAddress: HomePageAddress,
    @Embedded
    val phone: Phone,
    // TODO :: 평점
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var division: Division? = null

    @Enumerated(EnumType.STRING)
    var kind: Kind? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val students = mutableListOf<Student>()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    var wiki: Wiki? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val teachers = mutableListOf<Teacher>()

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}