package com.df.dao;

import com.df.domain.Role;
import com.df.envconfig.mybatis.MybatisLanguageDriver;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
@Mapper
public interface RoleMapper {

    @Lang(MybatisLanguageDriver.class)
    @Select("SELECT * FROM t_role WHERE id IN (#{roleIds})")
    List<Role> findRolesInRoleIds(@Param("roleIds")List<Long> roleIds);
}
