package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Status
import java.time.LocalDate

class AdInfo {

    class Main(
        val id: String,
        val companyName: String,
        val views: Int,
        val clicks: Int,
        val image: String,
        val link: String,
        val expirationDate: LocalDate,
        val status: Status,
    )
}