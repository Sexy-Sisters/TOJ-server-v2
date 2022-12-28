package com.sexysisters.tojserverv2.domain.feed.domain

import com.sexysisters.tojserverv2.domain.BaseTimeEntity
import com.sexysisters.tojserverv2.domain.student.domain.Student
import javax.persistence.*

@Entity
@Table(name = "tbl_feed")
class Feed(
    @ManyToOne
    val writer: Student,
    val content: Content,
    @ElementCollection
    val images: List<Image>
) : BaseTimeEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}