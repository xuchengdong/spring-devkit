package com.df.component.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/8/18.
 */
@Component
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String msg) {
        jmsTemplate.convertAndSend("someQueue", String.format("Hello %s!", msg));
    }
}
