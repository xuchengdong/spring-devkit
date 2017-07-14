package com.df.dao;

import com.df.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM hyip_user_route WHERE id = #{id}")
    User findUserById(@Param("id") Long id);
}
