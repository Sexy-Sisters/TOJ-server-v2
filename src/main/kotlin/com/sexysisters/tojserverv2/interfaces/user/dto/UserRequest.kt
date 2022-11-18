package com.sexysisters.tojserverv2.interfaces.user.dto

import javax.validation.constraints.NotBlank

class UserRequest {

    data class Create(
        @field:NotBlank(message = "name이 비어있습니다.")
        val name: String,

        @field:NotBlank(message = "nickname이 비어있습니다.")
        val nickname: String,

        @field:NotBlank(message = "email이 비어있습니다.")
        val email: String,

        @field:NotBlank(message = "password가 비어있습니다.")
        val password: String,
    )
}