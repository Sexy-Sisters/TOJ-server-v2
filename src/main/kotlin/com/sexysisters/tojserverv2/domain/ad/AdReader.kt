package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import com.sexysisters.tojserverv2.infrastructure.ad.Sort

interface AdReader {
    fun getAd(id: Long): Ad
    fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<Ad>
}