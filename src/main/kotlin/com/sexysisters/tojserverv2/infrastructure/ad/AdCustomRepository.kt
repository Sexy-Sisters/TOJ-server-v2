package com.sexysisters.tojserverv2.infrastructure.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status

interface AdCustomRepository {
    fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<Ad>
}

enum class Sort {
    VIEWS, COST
}