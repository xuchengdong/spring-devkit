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
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User findUserById(@Param("id") Long id);

    @Select("SELECT user_id FROM t_user WHERE user_name = #{userName}")
    Long findUserIdByUserName(String userName);
}
