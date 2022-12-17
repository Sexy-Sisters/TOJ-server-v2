package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.school.type.Division
import com.sexysisters.tojserverv2.domain.school.type.Kind
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.teacher.Teacher
import com.sexysisters.tojserverv2.domain.wiki.Wiki
import javax.persistence.*

@Entity
@Table(name = "tbl_school")
class School(
    val code: String,
    val belong: String,
    val name: String,
    val address: String,
    val birthDay: String,
    val homePageAddress: String,
    val phone: String,
    // TODO :: 평점
) : BaseTimeEntity() {

    @Enumerated(EnumType.STRING)
    var division: Division? = null

    @Enumerated(EnumType.STRING)
    var kind: Kind? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val studentList = mutableListOf<Student>()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wiki_id")
    var wiki: Wiki? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val teachers = mutableListOf<Teacher>()

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (code.isBlank()) throw SchoolException.SchoolNotValid()
        if (belong.isBlank()) throw SchoolException.SchoolNotValid()
        if (name.isBlank()) throw SchoolException.SchoolNotValid()
        if (address.isBlank()) throw SchoolException.SchoolNotValid()
        if (birthDay.isBlank()) throw SchoolException.SchoolNotValid()
        if (homePageAddress.isBlank()) throw SchoolException.SchoolNotValid()
        if (phone.isBlank()) throw SchoolException.SchoolNotValid()
    }
}

fun School.makeRelation(student: Student) {
    this.studentList.add(student)
    student.school = this
}