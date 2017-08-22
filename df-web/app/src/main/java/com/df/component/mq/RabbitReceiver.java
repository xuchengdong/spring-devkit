package com.df.component.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/8/21.
 */
@Component
public class RabbitReceiver {

    @RabbitListener(queues = "spring-boot")
    public void processMessage(String content) {
        // ...
        System.out.println(content);
    }
}
