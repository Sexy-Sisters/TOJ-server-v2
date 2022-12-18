package com.sexysisters.tojserverv2.infrastructure.redis

import java.time.Duration

interface RedisRepository {
    fun getData(key: String): Any?
    fun setData(
        key: String,
        value: String
    )

    fun setDataExpired(
        key: String,
        value: String,
        duration: Duration,
    )

    fun deleteData(key: String): Boolean
    fun setBlackList(
        key: String,
        duration: Duration
    )

    fun hasBlackList(key: String): Boolean
}