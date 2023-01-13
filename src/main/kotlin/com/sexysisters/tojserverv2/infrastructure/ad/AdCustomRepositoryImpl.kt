package com.sexysisters.tojserverv2.infrastructure.ad

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import com.sexysisters.tojserverv2.domain.ad.domain.custinfo.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.QAd.ad
import com.sexysisters.tojserverv2.domain.ad.domain.Status
import org.springframework.stereotype.Component

@Component
class AdCustomRepositoryImpl(
    private val query: JPAQueryFactory,
) : AdCustomRepository {

    override fun getAdList(
        status: Status,
        adKind: AdKind?,
        sort: Sort,
    ): List<Ad> {
        return query.selectFrom(ad)
            .where(
                statusEq(status),
                adKindEq(adKind),
            )
            .orderBy(
                selectStandard(sort)
            )
            .fetch()
    }

    private fun statusEq(status: Status) = ad.status.eq(status)

    private fun adKindEq(adKind: AdKind?): BooleanExpression? {
        if (adKind != null) {
            return ad.adKind.eq(adKind)
        }
        return null
    }

    private fun selectStandard(sort: Sort) = when (sort) {
        Sort.VIEWS -> ad.costInfo.views.desc()
        else -> null
    }
}