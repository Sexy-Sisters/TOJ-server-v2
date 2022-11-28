package com.sexysisters.tojserverv2.config.properties

import java.time.Duration

class MailProperties {
    companion object {
        val AUTHENTICATION_TITLE = "[TOJ] 인증 코드 발송"
        val AUTHENTCIATION_TIME = Duration.ofMillis(1000L * 60 * 3L)

        val SIGNUP_TITLE = "[TOJ] 가입을 축하드립니다"
        val SIGNUP_DESCRIBTION = "🧑🏽‍🎓♻️👩‍🏫 학생과 선생님의 의존성 역전에 동참하신 것을 축하드립니다!!"
    }
}