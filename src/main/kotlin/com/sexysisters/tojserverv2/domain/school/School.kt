package com.sexysisters.tojserverv2.domain.school

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.school.type.Division
import com.sexysisters.tojserverv2.domain.school.type.Kind
import org.apache.commons.lang3.StringUtils
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (StringUtils.isEmpty(code)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(belong)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(name)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(address)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(birthDay)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(homePageAddress)) throw SchoolException.SchoolEmptyProperties()
        if (StringUtils.isEmpty(phone)) throw SchoolException.SchoolEmptyProperties()
    }
}

fun School.makeRelation(student: Student) {
    this.studentList.add(student)
    student.school = this
}