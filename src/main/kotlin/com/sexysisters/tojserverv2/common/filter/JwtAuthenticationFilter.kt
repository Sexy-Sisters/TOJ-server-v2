package com.sexysisters.tojserverv2.common.filter

import com.sexysisters.tojserverv2.common.security.auth.AuthDetailsService
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtValidator
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val authDetailsService: AuthDetailsService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtValidator: JwtValidator,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = jwtTokenProvider.resolveToken(request)
        if (accessToken != null) {
            setAuthentication(accessToken, request)
        }
        filterChain.doFilter(request, response)
    }

    private fun setAuthentication(
        token: String,
        request: HttpServletRequest,
    ) {
        val email = jwtValidator.getEmail(token)
        val userDetails = authDetailsService.loadUserByUsername(email)
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }
}