package com.sexysisters.tojserverv2.interfaces.ad.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.sexysisters.tojserverv2.domain.ad.domain.AdKind
import com.sexysisters.tojserverv2.domain.ad.domain.Type
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AdRequest {

    class Create(
        val adKind: AdKind,
        @field:NotBlank
        val image: String,
        @field:NotBlank
        val link: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        val expirationDate: LocalDate,

        val costType: Type,
        @field:NotNull
        val cost: Int,

        @field:NotBlank
        val companyName: String,
        @field:NotBlank
        val advertiser: String,
    )
}