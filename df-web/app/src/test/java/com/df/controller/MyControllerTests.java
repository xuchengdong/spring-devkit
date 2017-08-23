package com.df.controller;

import com.df.domain.Greeting;
import com.df.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestGreetingController.class)
public class MyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void testExample() throws Exception {
        Greeting expected = new Greeting(1, "Hello");

        // 测试中发现 actual 一直为null,未报异常
        Greeting actual = this.greetingService.greeting(1L, "Hello");
        given(actual).willReturn(expected);

        this.mvc.perform(get("/test/restGreeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"content\":\"Hello, World!\"}"));
    }

}
