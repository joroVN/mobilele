package com.softuni.mobilele.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {


    @Bean
    public JavaMailSender javaMailSender(
            @Value("$(mail.host)") String mailHost,
//            @Value("$(mail.port)") Integer mailPort,
            @Value("$(mail.username)") String username,
            @Value("$(mail.password)") String password
    ) {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(465);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(mailProperties());

        return javaMailSender;
    }

    private Properties mailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        return properties;
    }
}
