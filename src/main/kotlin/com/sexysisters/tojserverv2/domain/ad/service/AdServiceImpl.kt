package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.AdCommand
import com.sexysisters.tojserverv2.domain.ad.AdReader
import com.sexysisters.tojserverv2.domain.ad.AdStore
import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.Company
import com.sexysisters.tojserverv2.domain.ad.domain.CostInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdServiceImpl(
    private val adStore: AdStore,
    private val adReader: AdReader,
) : AdService {

    @Transactional
    override fun openAd(command: AdCommand.Create) {
        val initCostInfo = CostInfo(
            type = command.costType,
            cost = command.cost,
        )
        val initCompany = Company(
            name = command.companyName,
            advertiser = command.advertiser,
        )
        val initAd = Ad(
            adKind = command.adKind,
            image = command.image,
            link = command.link,
            expirationDate = command.expirationDate,
            costInfo = initCostInfo,
            company = initCompany,
        )
        adStore.store(initAd)
    }

    @Transactional
    override fun deleteAd(id: Long) {
        val ad = adReader.getAd(id)
        adStore.delete(ad)
    }
}