package com.qf.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductType implements Serializable {
    private Long id;

    private String name;

    private Long pid;

    private Byte flag;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updataUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdataUser() {
        return updataUser;
    }

    public void setUpdataUser(Long updataUser) {
        this.updataUser = updataUser;
    }
}