package com.sexysisters.tojserverv2.common.security.auth

import com.sexysisters.tojserverv2.domain.user.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    val user: User,
) : UserDetails {

    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(user.authority.name))

    override fun getPassword() = this.user.password

    override fun getUsername() = this.user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}