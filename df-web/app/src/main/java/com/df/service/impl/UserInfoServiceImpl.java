package com.df.service.impl;

import com.df.dao.UserInfoMapper;
import com.df.domain.UserInfo;
import com.df.envconfig.datasource.TargetDataSource;
import com.df.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/19.
 */
@Service
@TargetDataSource(name = "user")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoMapper.findUserInfoByUserId(userId);
    }
}
