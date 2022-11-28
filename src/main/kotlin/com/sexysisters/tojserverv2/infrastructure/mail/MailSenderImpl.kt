package com.sexysisters.tojserverv2.infrastructure.mail

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailSenderImpl(
    var javaMailSender: JavaMailSender,
) : MailSender {

    override fun sendMail(
        to: String,
        title: String,
        content: String,
    ) {
        val email = SimpleMailMessage()
        email.setSubject(title)
        email.setText(content)
        email.setTo(to)
        javaMailSender.send(email)
    }
}