package com.sexysisters.tojserverv2.domain.notification

interface NotificationService {
    fun sendEmail(email: String, title: String, description: String,)
    fun sendKakao(phoneNo: String, description: String,)
    fun sendSms(phoneNo: String, description: String,)
}