package com.sexysisters.tojserverv2.config.properties

import java.time.Duration

class MailProperties {
    companion object {
        val AUTHENTICATION_TITLE = "[TOJ] 인증 코드 발송"
        val AUTHENTCIATION_TIME = Duration.ofMillis(1000L * 60 * 3L)
    }
}