package com.qf.dto;

import java.io.Serializable;
import com.qf.entity.User;

public class UuidAndUser implements Serializable{

    String uuid;
    User user;

    public UuidAndUser(String uuid, User user) {
        this.uuid = uuid;
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
