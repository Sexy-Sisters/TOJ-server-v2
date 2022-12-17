package com.sexysisters.tojserverv2.infrastructure.ad

import com.sexysisters.tojserverv2.domain.ad.AdReader
import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import com.sexysisters.tojserverv2.domain.ad.exception.AdException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AdReaderImpl(
    private val adRepository: AdRepository,
) : AdReader {

    override fun getAd(id: Long) =
        adRepository.findByIdOrNull(id)
            ?: throw AdException.AdNotFound()

    override fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<Ad> {
        return adRepository.getAdList(
            status,
            adKind,
            sort,
        )
    }
}