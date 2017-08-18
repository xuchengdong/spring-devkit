package com.df.domain;

import java.io.Serializable;

/**
 * @author xuchengdong@qbao.com on 2017/8/15.
 */
public class Role implements Serializable{
    private static final long serialVersionUID = -154589437839393511L;
    private Long id;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
