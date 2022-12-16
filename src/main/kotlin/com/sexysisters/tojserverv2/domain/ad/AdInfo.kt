package com.sexysisters.tojserverv2.domain.ad

import java.time.LocalDate

class AdInfo {

    class Main(
        val id: Long,
        val companyName: String,
        val views: Int,
        val image: String,
        val link: String,
        val expirationDate: LocalDate,
    )
}