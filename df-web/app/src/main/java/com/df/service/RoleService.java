package com.df.service;

import com.df.domain.Role;

import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
public interface RoleService {
    List<Role> findRolesInRoleIds(List<Long> roleIds);
}
