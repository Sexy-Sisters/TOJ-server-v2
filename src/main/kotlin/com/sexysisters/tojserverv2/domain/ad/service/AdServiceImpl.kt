package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.*
import com.sexysisters.tojserverv2.domain.ad.domain.*
import com.sexysisters.tojserverv2.infrastructure.ad.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdServiceImpl(
    private val adStore: AdStore,
    private val adReader: AdReader,
    private val adMapper: AdMapper,
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

    @Transactional(readOnly = true)
    override fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<AdInfo.Main> {
        return adReader.getAdList(
            status,
            adKind,
            sort,
        ).map { adMapper.of(it) }
    }
}