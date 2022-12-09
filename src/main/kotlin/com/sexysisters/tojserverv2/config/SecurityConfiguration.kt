package com.sexysisters.tojserverv2.config

import com.sexysisters.tojserverv2.common.security.auth.AuthDetailsService
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtValidator
import com.sexysisters.tojserverv2.common.filter.JwtAuthenticationFilter
import com.sexysisters.tojserverv2.common.filter.JwtExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val authDetailsService: AuthDetailsService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtValidator: JwtValidator,
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()

            // auth
            .antMatchers(HttpMethod.POST, "/api/v2/auth").anonymous()
            .antMatchers("/api/v2/auth/code").anonymous()

            // oauth
            .antMatchers("/api/v2/oauth/**").anonymous()

            // user
            .antMatchers(HttpMethod.POST, "/api/v2/user").anonymous()

            // school
            .antMatchers(HttpMethod.GET, "/api/v2/school").permitAll()
            .anyRequest().authenticated()

        http
            .addFilterBefore(
                JwtAuthenticationFilter(
                    authDetailsService,
                    jwtTokenProvider,
                    jwtValidator,
                ),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JwtExceptionFilter(),
                JwtAuthenticationFilter::class.java
            )

        return http.build()
    }
}