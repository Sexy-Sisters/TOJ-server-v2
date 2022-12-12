package com.sexysisters.tojserverv2.config

import com.sexysisters.tojserverv2.config.properties.MailSenderProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
@EnableConfigurationProperties(MailSenderProperties::class)
class MailSenderConfiguration(
    private val mailSenderProperties: MailSenderProperties,
) {

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl().apply {
            host = mailSenderProperties.host
            port = mailSenderProperties.port
            username = mailSenderProperties.username
            password = mailSenderProperties.password
        }
        configureJavaMailProperties(mailSender.javaMailProperties)
        return mailSender
    }

    private fun configureJavaMailProperties(properties: Properties) {
        properties.also {
            it["mail.transport.protocol"] = mailSenderProperties.protocol
            it["mail.smtp.auth"] = mailSenderProperties.auth
            it["mail.smtp.starttls.enable"] = mailSenderProperties.starttlsEnable
        }
    }
}