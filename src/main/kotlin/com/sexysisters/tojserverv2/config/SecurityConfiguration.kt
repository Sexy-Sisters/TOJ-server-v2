package com.sexysisters.tojserverv2.config

import com.sexysisters.tojserverv2.common.filter.JwtAuthenticationFilter
import com.sexysisters.tojserverv2.common.filter.JwtExceptionFilter
import com.sexysisters.tojserverv2.common.security.auth.AuthDetailsService
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtTokenProvider
import com.sexysisters.tojserverv2.infrastructure.jwt.JwtValidator
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
    private val VERSION = "/api/v2"

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors()

        http
            .authorizeRequests()

            // swagger
            .antMatchers(
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**"
            ).permitAll()

            // auth
            .antMatchers(HttpMethod.POST, "$VERSION/auth").permitAll()
            .antMatchers(HttpMethod.PUT, "$VERSION/auth").permitAll()

            // TODO :: 인증 코드 발급 인증 안하고 허용하기 (계속 토큰 만료 에러뜸)
            .antMatchers("$VERSION/uth/code").permitAll()

            // oauth
            .antMatchers("$VERSION/oauth/**").permitAll()

            // user
            .antMatchers(HttpMethod.POST, "$VERSION/user").permitAll()

            // school
            .antMatchers(HttpMethod.GET, "$VERSION/school").permitAll()

            // IMG
            .antMatchers(HttpMethod.POST, "$VERSION/image").permitAll()

            // TODO:: for test / change range of authority
            .antMatchers("/api/v2/ad").permitAll()

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