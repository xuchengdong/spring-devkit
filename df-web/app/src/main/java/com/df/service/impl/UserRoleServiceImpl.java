package com.df.service.impl;

import com.df.dao.UserRoleMapper;
import com.df.domain.Role;
import com.df.domain.UserRole;
import com.df.multipleds.spring.boot.autoconfigure.TargetDataSource;
import com.df.service.RoleService;
import com.df.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
@Service
@TargetDataSource(name = "user")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    RoleService roleService;

    @Override
    public UserRole findUserRoleByUserId(Long userId) {
        if(userId == null){
            return null;
        }

        List<Role> roles = new ArrayList<>();

        List<Long> roleIds = userRoleMapper.findRoleIdsByUserId(userId);
        if (roleIds != null && !roleIds.isEmpty()) {
            roles = roleService.findRolesInRoleIds(roleIds);
        }
        return new UserRole(userId, roles);
    }
}
