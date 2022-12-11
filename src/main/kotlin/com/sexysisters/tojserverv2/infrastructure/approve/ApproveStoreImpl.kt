package com.sexysisters.tojserverv2.infrastructure.approve

import com.sexysisters.tojserverv2.domain.approve.Approve
import com.sexysisters.tojserverv2.domain.approve.ApproveStore
import com.sexysisters.tojserverv2.domain.approve.exception.ApproveException
import org.springframework.stereotype.Component

@Component
class ApproveStoreImpl(
    private val approveRepository: ApproveRepository,
) : ApproveStore {

    override fun store(approve: Approve): Approve {
        return approveRepository.save(approve)
    }

    override fun delete(approve: Approve) {
        if (!approveRepository.existsById(approve.id)) {
            throw ApproveException.ApproveNotFound()
        }
        approveRepository.delete(approve)
    }
}