package com.pavel.forumapplication.Email;

public interface EmailSender {
    void Send(String setFrom,String setTo, String subject, String text);
}
