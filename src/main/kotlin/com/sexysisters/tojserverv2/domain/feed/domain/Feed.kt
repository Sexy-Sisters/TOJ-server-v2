package com.sexysisters.tojserverv2.domain.feed.domain

import com.sexysisters.tojserverv2.domain.BaseEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_feed")
class Feed(
    content: Content,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    val writer: Student,

    @ElementCollection
    val images: List<Image>
) : BaseEntity() {

    var content: Content = content
        private set

    fun update(content: Content) {
        this.content = content
    }

    fun contentValue() = content.value
    fun imageValues() = images.map { it.value }
}