package com.sexysisters.tojserverv2.infrastructure.neis.dto

data class SchoolInfoResponse(
    val code: String,
    val belong: String,
    val name: String,
    val division: String,
    val address: String,
    val birthday: String,
    val homePageAddress: String,
    val phone: String,
)