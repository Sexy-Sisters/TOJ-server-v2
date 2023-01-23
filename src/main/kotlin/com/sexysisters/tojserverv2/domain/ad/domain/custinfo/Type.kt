package com.sexysisters.tojserverv2.domain.ad.domain.custinfo

enum class Type(
    val description: String,
) {
    IMP("노출당 비용"),
    CPC("클릭당 비용"),
    CMP("1,000번 노출당 비용"),
}