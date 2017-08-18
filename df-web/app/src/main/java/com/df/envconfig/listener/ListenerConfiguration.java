package com.df.envconfig.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
@Configuration
public class ListenerConfiguration {

    @Bean
    public HttpSessionEventPublisher registration() {
        return new HttpSessionEventPublisher();
    }

}
