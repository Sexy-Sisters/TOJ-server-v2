package com.sexysisters.tojserverv2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@ConfigurationPropertiesScan
@SpringBootApplication
class TojServerV2Application

fun main(args: Array<String>) {
    runApplication<TojServerV2Application>(*args)
}