package com.sexysisters.tojserverv2.interfaces.approve.dto

import com.sexysisters.tojserverv2.domain.approve.ApproveCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface ApproveDtoMapper {
    // request
    fun of(request: ApproveRequest.Approve): ApproveCommand.Approve

    fun of(request: ApproveRequest.Cancel): ApproveCommand.Cancel
    // response
}