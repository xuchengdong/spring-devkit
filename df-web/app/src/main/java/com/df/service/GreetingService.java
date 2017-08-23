package com.df.service;

import com.df.domain.Greeting;

/**
 * @author xuchengdong@qbao.com on 2017/8/23.
 */
public interface GreetingService {
    Greeting greeting(Long id, String content);
}
