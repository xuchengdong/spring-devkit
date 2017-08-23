package com.df.controller;

import com.df.component.mail.EmailSender;
import com.df.component.mq.JmsSender;
import com.df.component.mq.RabbitSender;
import com.df.domain.UserInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/8/21.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private JmsSender jmsSender;

    @Resource
    private RabbitSender rabbitSender;

    @Resource
    private EmailSender emailSender;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/sendJms/{msg}")
    public String sendJms(@PathVariable(name = "msg") String msg) {
        jmsSender.sendMessage(msg);
        return "ok";
    }

    @RequestMapping("/sendRabbit/{msg}")
    public String sendRabbit(@PathVariable(name = "msg") String msg) {
        rabbitSender.sendMessage(msg);
        return "ok";
    }

    @RequestMapping("/sendMail/{msg}")
    public String sendMail(@PathVariable(name = "msg") String msg) {
        emailSender.sendMessage(msg);
        return "ok";
    }

    @RequestMapping("/getUser/{userId}")
    public UserInfo getUser(@PathVariable(name = "userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://df.qbao.com/user/userInfo/" + userId, UserInfo.class);
    }
}
