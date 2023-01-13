package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.custinfo.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.custinfo.Type
import java.time.LocalDate

class AdCommand {

    class Create(
        val adKind: AdKind,
        val image: String,
        val link: String,
        val expirationDate: LocalDate,

        val costType: Type,
        val cost: Int,

        val companyName: String,
        val advertiser: String,
    )
}