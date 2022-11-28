package com.sexysisters.tojserverv2.infrastructure.mail

interface MailSender {
    fun sendMail(to: String, title: String, content: String)
}