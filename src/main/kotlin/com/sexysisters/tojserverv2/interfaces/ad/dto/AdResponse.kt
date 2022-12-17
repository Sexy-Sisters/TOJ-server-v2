package com.sexysisters.tojserverv2.interfaces.ad.dto

import com.sexysisters.tojserverv2.domain.ad.domain.Status
import java.time.LocalDate

class AdResponse {

    class Main(
        val id: Long,
        val companyName: String,
        val views: Int,
        val image: String,
        val link: String,
        val expirationDate: LocalDate,
        val status: Status,
    )
}