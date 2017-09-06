package com.df.service;

import com.df.domain.User;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
public interface UserService {
    User findUserById(Long id);

    Long findUserIdByUserName(String userName);
}
