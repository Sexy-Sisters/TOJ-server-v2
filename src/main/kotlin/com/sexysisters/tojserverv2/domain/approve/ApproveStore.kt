package com.sexysisters.tojserverv2.domain.approve

interface ApproveStore {
    fun store(approve: Approve): Approve
    fun delete(approve: Approve)
}