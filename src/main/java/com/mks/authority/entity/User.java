package com.mks.authority.entity;

import java.util.Date;
import java.util.List;
 
public class User {

    public static final int USEABLE = 1;
    public static final int UN_UNSEABLE = 0;
    
    private Long id;
    private String loginName;
    private String username;
    private String password;
    private Integer useable;
    private String email;
    private String telephone;
    private Date createTime;
    private Date modifyTime;
    
    private List<Role> roles;
    
    public boolean isUseable() {
        return useable == USEABLE;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUseable() {
        return useable;
    }
    
    public void setUseable(Integer useable) {
        this.useable = useable;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getModifyTime() {
        return modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
