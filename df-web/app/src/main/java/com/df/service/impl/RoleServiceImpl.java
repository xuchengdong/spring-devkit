package com.df.service.impl;

import com.df.dao.RoleMapper;
import com.df.domain.Role;
import com.df.multipleds.spring.boot.autoconfigure.TargetDataSource;
import com.df.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
@Service
@TargetDataSource(name="df")
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> findRolesInRoleIds(List<Long> roleIds) {
        return roleMapper.findRolesInRoleIds(roleIds);
    }
}
