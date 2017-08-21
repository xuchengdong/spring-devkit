package com.df.component.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/8/18.
 */
@Component
public class JmsConsumers {

    @JmsListener(destination = "someQueue")
    public void processMessage(String content) {
        // ...
        System.out.println(content);
    }

}
