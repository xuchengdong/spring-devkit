package com.df.service.impl;

import com.df.dao.UserMapper;
import com.df.domain.User;
import com.df.envconfig.datasource.TargetDataSource;
import com.df.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
@Service
@TargetDataSource(name="df")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}
