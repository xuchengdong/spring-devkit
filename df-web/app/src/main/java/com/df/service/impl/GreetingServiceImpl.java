package com.df.service.impl;

import com.df.domain.Greeting;
import com.df.service.GreetingService;
import org.springframework.stereotype.Service;

/**
 * @author xuchengdong@qbao.com on 2017/8/23.
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public Greeting greeting(Long id, String content) {
        return new Greeting(id, content);
    }
}
