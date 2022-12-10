package com.sexysisters.tojserverv2.domain.approve.service

import com.sexysisters.tojserverv2.domain.approve.ApproveCommand

interface ApproveService {
    fun approve(command: ApproveCommand.Approve)
}