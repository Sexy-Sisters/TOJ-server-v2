package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.*
import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.Company
import com.sexysisters.tojserverv2.domain.ad.domain.CostInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdServiceImpl(
    private val adStore: AdStore,
) : AdService {

    @Transactional
    override fun createAd(command: AdCommand.Create) {
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
}