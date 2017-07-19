package com.df.service;

import com.df.domain.UserInfo;

/**
 * @author xuchengdong@qbao.com on 2017/7/19.
 */
public interface UserInfoService {
    UserInfo findUserInfoByUserId(Long userId);
}
