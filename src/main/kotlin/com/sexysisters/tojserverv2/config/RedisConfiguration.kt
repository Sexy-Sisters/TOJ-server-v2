package com.sexysisters.tojserverv2.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfiguration(
    @Value("\${spring.redis.host}") val host: String,

    @Value("\${spring.redis.port}") val port: Int,
) {
    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(host, port)

    @Bean
    fun stringRedisTemplate() = StringRedisTemplate().apply {
        keySerializer = StringRedisSerializer()
        valueSerializer = StringRedisSerializer()
        setConnectionFactory(redisConnectionFactory())
    }
}