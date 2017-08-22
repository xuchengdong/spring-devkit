package com.df.component.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/8/18.
 */
@Component
public class JmsReceiver {

    private static final Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = "someQueue")
    public void processMessage(String content) {
        // ...
        logger.info("[{}] Receiving message: {}", "someQueue", content);
    }

}
