package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.school.type.Division
import com.sexysisters.tojserverv2.domain.school.type.Kind
import com.sexysisters.tojserverv2.domain.teacher.Teacher
import com.sexysisters.tojserverv2.domain.wiki.Wiki
import org.apache.commons.lang3.StringUtils
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

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

    @OneToOne(mappedBy = "school", fetch = FetchType.LAZY)
    var wiki: Wiki? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val teachers = mutableListOf<Teacher>()

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (StringUtils.isEmpty(code)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(belong)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(name)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(address)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(birthDay)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(homePageAddress)) throw SchoolException.SchoolNotValid()
        if (StringUtils.isEmpty(phone)) throw SchoolException.SchoolNotValid()
    }
}

fun School.makeRelation(student: Student) {
    this.studentList.add(student)
    student.school = this
}