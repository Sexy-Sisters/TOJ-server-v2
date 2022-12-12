package com.sexysisters.tojserverv2.infrastructure.neis.dto

import com.sexysisters.tojserverv2.domain.school.School

data class NeisSchoolResponse constructor(
    val code: String,
    val belong: String,
    val name: String,
    val address: String,
    val birthday: String,
    val homePageAddress: String,
    val phone: String,
    val division: String,
    val kind: String?,
) {
    fun toEntity() =
        School(
            code = this.code,
            belong = this.belong,
            name = this.name,
            address = this.address,
            birthDay = this.birthday,
            homePageAddress = this.homePageAddress,
            phone = this.phone,
        )
}