package com.df.component.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/8/21.
 */
@Component
public class RabbitReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    @RabbitListener(queues = "spring-boot")
    public void processMessage(String content) {
        // ...
        logger.info("[{}] Receiving message: {}", "spring-boot", content);
    }
}
