package com.df.domain;

/**
 * @author xuchengdong@qbao.com on 2017/7/14.
 */
public class User {
    private Long id;
    private String username;
    private String mobile;
    private String email;

    public User() {
    }

    public User(Long id, String username, String mobile, String email) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
