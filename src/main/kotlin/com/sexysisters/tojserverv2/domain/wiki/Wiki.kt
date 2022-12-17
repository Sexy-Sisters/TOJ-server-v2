package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.config.properties.WikiProperties
import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import javax.persistence.*

@Entity
@Table(name = "tbl_wiki")
class Wiki(
    val name: String,
) {
    var html: String = WikiProperties.EMPTY
    var markdown: String = WikiProperties.EMPTY
    var views: Int = 0

    @OneToOne(mappedBy = "wiki", fetch = FetchType.LAZY)
    var school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    init {
        if (name.isBlank()) throw WikiException.WikiNotValid()
        if (html.isBlank()) throw WikiException.WikiNotValid()
        if (markdown.isBlank()) throw WikiException.WikiNotValid()
        if (views < 0) throw WikiException.WikiNotValid()
    }
}

fun Wiki.makeRelation(school: School) {
    this.school = school
    school.wiki = this
}

fun Wiki.update(html: String, markdown: String) {
    this.html = html
    this.markdown = markdown
}

fun Wiki.countViews() {
    this.views++
}