package com.df.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchengdong@qbao.com on 2017/7/28.
 */
@RestController
@RequestMapping("/test")
public class PropertiesOrderCase {

    @Value("${name}")
    private String name;

    @RequestMapping("/getName")
    public String getName() {
        return name;
    }
}
