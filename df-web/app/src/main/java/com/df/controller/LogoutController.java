package com.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuchengdong@qbao.com on 2017/8/11.
 */
@Controller
public class LogoutController {

    @RequestMapping("/logoutSuccess")
    public String logout(Model model) {
        return "logout";
    }

}
