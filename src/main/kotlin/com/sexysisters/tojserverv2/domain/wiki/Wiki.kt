package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import org.apache.commons.lang3.StringUtils
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tbl_wiki")
class Wiki(
    val name: String,
    val content: String = "🌕🌖🌗🌘🌑🌒🌓🌔🌕\n 자유롭게 입력해주세요!",
) {

    @OneToOne(fetch = FetchType.LAZY)
    var school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (StringUtils.isEmpty(name)) throw WikiException.WikiNotValid()
        if (StringUtils.isEmpty(content)) throw WikiException.WikiNotValid()
    }
}

fun Wiki.makeRelation(school: School) {
    this.school = school
    school.wiki = this
}