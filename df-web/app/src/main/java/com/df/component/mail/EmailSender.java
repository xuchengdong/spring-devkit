package com.df.component.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xuchengdong@qbao.com on 2017/8/22.
 */
@Component
public class EmailSender {

    private JavaMailSenderImpl mailSender;

    public EmailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender.getUsername());
        simpleMailMessage.setTo("xuchengdongxcd@126.com");
        simpleMailMessage.setSubject("Hello World!");
        simpleMailMessage.setText(String.format("Test JavaMailSender! %s", msg));
        simpleMailMessage.setSentDate(new Date());
        mailSender.send(simpleMailMessage);
    }
}
