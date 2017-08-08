package com.df.controller;

import com.df.domain.User;
import com.df.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/findUserById")
    public User findUserById(@RequestParam(value = "userId") Long userId) {
        return userService.findUserById(userId);
    }

    @RequestMapping("/{userId}")
    public User findUserById4PathVar(@PathVariable(name = "userId") Long userId) {
        return userService.findUserById(userId);
    }
}
