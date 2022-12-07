package com.pavel.forumapplication.Email.impl;

import com.pavel.forumapplication.Email.EmailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;


    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void Send(String setFrom, String setTo, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setFrom(setFrom);
            simpleMailMessage.setTo(setTo);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
        }
        catch (MailException mailException) {
            javaMailSender.send(simpleMailMessage);
        }
    }
}
