package com.df.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
public class UserRole implements Serializable{
    private static final long serialVersionUID = 1588258575631224824L;
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
