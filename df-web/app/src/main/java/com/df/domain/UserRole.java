package com.df.domain;

import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
public class UserRole {
    private Long userId;
    private List<Role> roles;

    public UserRole(Long userId, List<Role> roles) {
        this.userId = userId;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
