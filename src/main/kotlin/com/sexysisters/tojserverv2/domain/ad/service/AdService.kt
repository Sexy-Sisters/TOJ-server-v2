package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.AdCommand
import com.sexysisters.tojserverv2.domain.ad.AdInfo
import com.sexysisters.tojserverv2.domain.ad.domain.custinfo.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import com.sexysisters.tojserverv2.infrastructure.ad.Sort
import java.util.*

interface AdService {
    fun openAd(command: AdCommand.Create)
    fun deleteAd(id: UUID)
    fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<AdInfo.Main>
}