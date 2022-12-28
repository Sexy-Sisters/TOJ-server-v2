package com.sexysisters.tojserverv2.domain.feed.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_feed")
class Feed(
    content: Content,

    @ManyToOne
    val writer: Student,

    @ElementCollection
    val images: List<Image>
) : BaseTimeEntity() {

    var content: Content = content
        private set

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    fun update(content: Content) {
        this.content = content
    }

    fun contentValue() = content.value
    fun imageValues() = images.map { it.value }
}