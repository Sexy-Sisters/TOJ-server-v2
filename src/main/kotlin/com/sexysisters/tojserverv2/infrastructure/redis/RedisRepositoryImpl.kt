package com.sexysisters.tojserverv2.infrastructure.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisRepositoryImpl(
    private val stringTemplate: StringRedisTemplate,
    private val blackListTemplate: StringRedisTemplate,
) : RedisRepository {

    override fun getData(key: String) = stringTemplate.opsForValue().get(key)

    override fun setData(key: String, value: String) = stringTemplate.opsForValue().set(key, value)

    override fun setDataExpired(
        key: String,
        value: String,
        duration: Duration
    ) = stringTemplate.opsForValue().set(key, value, duration)

    override fun deleteData(key: String) = stringTemplate.delete(key)

    override fun setBlackList(key: String, duration: Duration) = blackListTemplate.opsForValue().set(key, "LOGOUT", duration)

    override fun hasBlackList(key: String) = blackListTemplate.hasKey(key)
}