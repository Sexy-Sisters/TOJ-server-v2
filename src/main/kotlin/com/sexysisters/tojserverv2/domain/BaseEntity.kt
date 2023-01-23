package com.sexysisters.tojserverv2.domain

import com.github.f4b6a3.ulid.UlidCreator
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : Persistable<UUID> {

    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid()

    @CreatedDate
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.MIN
        protected set

    @Transient
    private var _isNew = true

    override fun getId() = this.id

    override fun isNew() = _isNew

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

    override fun hashCode() = Objects.hashCode(id)

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as BaseEntity).id
        }
    }
}