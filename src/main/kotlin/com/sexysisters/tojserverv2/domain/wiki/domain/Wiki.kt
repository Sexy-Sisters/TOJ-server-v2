package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.domain.School
import javax.persistence.*

@Entity
@Table(name = "tbl_wiki")
class Wiki(
    @Embedded
    val name: Name,
    @Embedded
    var html: HTML,
    @Embedded
    var markdown: Markdown,
    @Embedded
    var views: Views,
) : BaseTimeEntity() {

    @OneToOne(mappedBy = "wiki", fetch = FetchType.LAZY)
    var school: School? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}

fun Wiki.makeRelation(school: School) {
    this.school = school
    school.wiki = this
}

fun Wiki.update(html: String, markdown: String) {
    this.html.value = html
    this.markdown.value = markdown
}

fun Wiki.countViews() {
    this.views.value++
}