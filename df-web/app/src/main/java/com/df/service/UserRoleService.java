package com.df.service;

import com.df.domain.UserRole;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
public interface UserRoleService {
    UserRole findUserRoleByUserId(Long userId);
}
