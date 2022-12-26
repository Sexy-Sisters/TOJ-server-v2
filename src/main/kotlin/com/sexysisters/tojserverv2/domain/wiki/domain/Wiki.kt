package com.sexysisters.tojserverv2.domain.wiki.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.school.domain.School
import javax.persistence.*

@Entity
@Table(name = "tbl_wiki")
class Wiki(
    @Embedded
    val name: Name
) : BaseTimeEntity() {

    @Embedded
    var html: HTML = HTML()
        private set

    @Embedded
    var markdown: Markdown = Markdown()
        private set

    @Embedded
    var views: Views = Views()
        private set

    @OneToOne(mappedBy = "wiki", fetch = FetchType.LAZY)
    var school: School? = null
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun makeRelation(school: School) {
        this.school = school
        school.wiki = this
    }

    fun update(html: HTML, markdown: Markdown) {
        this.html = html
        this.markdown = markdown
    }

    fun countViews() {
        val calculatedValue = viewsValue() + 1
        this.views = Views(calculatedValue)
    }

    fun nameValue() = name.value
    fun htmlValue() = html.value
    fun markdownValue() = markdown.value
    fun viewsValue() = views.value
}