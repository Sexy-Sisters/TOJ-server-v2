package com.sexysisters.tojserverv2.domain.school.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.teacher.domain.Teacher
import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import javax.persistence.*

@Entity
@Table(name = "tbl_school")
class School(
    @Embedded val code: Code,
    @Embedded val belong: Belong,
    @Embedded val name: Name,
    @Embedded val address: Address,
    @Embedded val birthday: Birthday,
    @Embedded val homePageAddress: HomePageAddress,
    @Embedded val phone: Phone,
    // TODO :: 평점
) : BaseTimeEntity() {

    @Embedded
    var wallpaper: Wallpaper = Wallpaper()
        private set

    @Enumerated(EnumType.STRING)
    var division: Division? = null

    @Enumerated(EnumType.STRING)
    var kind: Kind? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val students = mutableSetOf<Student>()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id")
    var wiki: Wiki? = null

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val teachers = mutableSetOf<Teacher>()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    val id: Long = 0L

    fun codeValue() = code.value
    fun belongValue() = belong.value
    fun nameValue() = name.value
    fun addressValue() = address.value
    fun birthdayValue() = birthday.value
    fun homePageAddressValue() = homePageAddress.value
    fun phoneValue() = phone.value
    fun wallPaperValue() = wallpaper.value

    fun updateWallpaper(wallpaper: String): String {
        this.wallpaper = Wallpaper(wallpaper)
        return wallPaperValue()
    }
}