package com.sexysisters.tojserverv2.infrastructure.neis.dto

import com.sexysisters.tojserverv2.domain.school.domain.*

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
            code = Code(this.code),
            belong = Belong(this.belong),
            name = Name(this.name),
            address = Address(this.address),
            birthday = Birthday(this.birthday),
            homePageAddress = HomePageAddress(this.homePageAddress),
            phone = Phone(this.phone),
        )
}