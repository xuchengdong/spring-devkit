package com.df.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
@Mapper
public interface UserRoleMapper {

    @Select("SELECT role_id FROM t_user_role WHERE user_id = #{userId}")
    List<Long> findRoleIdsByUserId(Long userId);
}
