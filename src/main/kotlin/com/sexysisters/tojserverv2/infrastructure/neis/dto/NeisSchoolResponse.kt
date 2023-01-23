package com.sexysisters.tojserverv2.infrastructure.neis.dto

import com.sexysisters.tojserverv2.domain.school.domain.*

data class NeisSchoolResponse constructor(
    val code: String,
    val beString: String,
    val name: String,
    val address: String,
    val birthday: String,
    val homePageAddress: String,
    val phone: String,
    val division: String,
    val kind: String?,
) {
    fun toEntity() = School(
        code = Code(code),
        belong = Belong(beString),
        name = Name(name),
        address = Address(address),
        birthday = Birthday(birthday),
        homePageAddress = HomePageAddress(homePageAddress),
        phone = Phone(phone),
    )
}