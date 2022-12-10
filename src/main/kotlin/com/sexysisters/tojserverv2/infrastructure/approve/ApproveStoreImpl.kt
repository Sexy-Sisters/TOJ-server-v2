package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.approve.ApproveStore
import org.springframework.stereotype.Component

@Component
class ApproveStoreImpl(
    private val approveRepository: ApproveRepository,
) : ApproveStore {

    override fun store(approve: Approve): Approve {
        return approveRepository.save(approve)
    }
}