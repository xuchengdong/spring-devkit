package com.df.service;

import com.df.dao.UserMapper;
import com.df.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}
