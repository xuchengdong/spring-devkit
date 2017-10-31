package com.df.controller;

import com.df.component.mail.EmailSender;
import com.df.component.mq.JmsSender;
import com.df.component.mq.RabbitSender;
import com.df.domain.Customer;
import com.df.domain.UserInfo;
import com.df.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerRepository repository;

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

    @RequestMapping("/mongodb")
    public String runMongoDB() {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
        return "ok";
    }
}
