package com.df.controller;

import com.df.domain.UserInfo;
import com.df.service.UserInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/19.
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/userInfo/{userId}")
    public UserInfo findUserInfoByUserId(@PathVariable(name = "userId") Long userId) {
        return userInfoService.findUserInfoByUserId(userId);
    }
}
