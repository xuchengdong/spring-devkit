package com.df.dao;

import com.df.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuchengdong@qbao.com on 2017/7/19.
 */
@Mapper
public interface UserInfoMapper {

    @Select("SELECT * FROM t_user_info WHERE user_id=#{userId}")
    UserInfo findUserInfoByUserId(Long userId);
}
