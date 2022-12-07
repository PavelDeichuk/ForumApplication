package com.pavel.forumapplication.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfiguration {

    @Value("${spring.mail.host}")
    private String host;


    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(5432);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        Properties properties = javaMailSender.getJavaMailProperties();
        return javaMailSender;
    }
}
