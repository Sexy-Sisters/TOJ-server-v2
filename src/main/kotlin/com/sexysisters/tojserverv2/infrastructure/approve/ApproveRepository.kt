package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.Approve
import org.springframework.data.jpa.repository.JpaRepository

interface ApproveRepository : JpaRepository<Approve, Long> {
}