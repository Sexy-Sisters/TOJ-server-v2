package com.sexysisters.tojserverv2.infrastructure

import com.sexysisters.tojserverv2.domain.notification.NotificationService
import com.sexysisters.tojserverv2.infrastructure.mail.MailSender
import org.springframework.stereotype.Component

@Component
class NotificationExecutor(
    private val mailSender: MailSender,
) : NotificationService {

    override fun sendEmail(email: String, title: String, description: String) {
        mailSender.sendMail(
            to = email,
            title = title,
            content = description,
        )
    }

    override fun sendKakao(phoneNo: String, description: String) {
//        TODO("카카오톡 메시지 전송 구현")
        println("sendKakao")
    }

    override fun sendSms(phoneNo: String, description: String) {
//        TODO("SMS 발송 구현")
        println("sendSms")
    }
}