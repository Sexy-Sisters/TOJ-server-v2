package com.sexysisters.tojserverv2.config

import com.sexysisters.tojserverv2.common.util.api.FeignClientErrorDecoder
import feign.Logger
import feign.codec.ErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(FeignClientErrorDecoder::class)
@Configuration
class FeginConfiguration {

    @Bean
    fun feignLoggerLevel() = Logger.Level.BASIC

    @Bean
    @ConditionalOnMissingBean(ErrorDecoder::class)
    fun commonFeignErrorDecoder() = FeignClientErrorDecoder()
}