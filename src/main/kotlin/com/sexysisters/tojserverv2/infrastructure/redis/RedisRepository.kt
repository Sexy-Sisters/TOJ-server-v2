package com.sexysisters.tojserverv2.infrastructure.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisRepository(
    private val stringTemplate: RedisTemplate<String, Any>,
    private val blackListTemplate: RedisTemplate<String, Any>,
) {

    fun getData(key: String) =
        stringTemplate.opsForValue().get(key)

    fun setData(key: String, value: String) =
        stringTemplate.opsForValue().set(key, value)

    fun setDataExpired(key: String, value: String, duration: Duration) =
        stringTemplate.opsForValue().set(key, value, duration)

    fun deleteData(key: String) =
        stringTemplate.delete(key)

    fun setBlackList(key: String, duration: Duration) =
        blackListTemplate.opsForValue().set(key, "LOGOUT", duration)

    fun hasBlackList(key: String) =
        blackListTemplate.hasKey(key)
}