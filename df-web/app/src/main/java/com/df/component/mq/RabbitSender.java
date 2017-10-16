package com.df.component.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuchengdong@qbao.com on 2017/8/21.
 */
@Configuration
public class RabbitSender {

    private final static String QUEUE_NAME = "spring-boot";
    private final static String EXCHANGE_NAME = "spring-boot-exchange";
    private final static String ROUTING_KEY = QUEUE_NAME;

    private RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    public void sendMessage(String msg) {
        rabbitTemplate.convertAndSend(ROUTING_KEY, msg);
    }

}
