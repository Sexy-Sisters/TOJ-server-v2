package com.sexysisters.tojserverv2.infrastructure.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.AdStore
import org.springframework.stereotype.Component

@Component
class AdStoreImpl(
    private val adRepository: AdRepository,
) : AdStore {

    override fun store(ad: Ad): Ad {
        return adRepository.save(ad)
    }
}