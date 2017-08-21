package com.df.component.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author xuchengdong@qbao.com on 2017/8/18.
 */
@Component
public class JmsProducers {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsProducers(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String msg) {
        jmsTemplate.send("someQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(String.format("Hello %s!", msg));
            }
        });
    }
}
