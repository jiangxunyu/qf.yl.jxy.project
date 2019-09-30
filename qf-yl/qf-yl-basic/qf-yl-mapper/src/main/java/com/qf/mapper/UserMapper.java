package com.qf.mapper;

import com.qf.base.IBaseDao;
import com.qf.entity.User;

public interface UserMapper extends IBaseDao<User> {

    User selectByUsername(String username);

    int insertUser(User user);

    int activeUser(Long id);
}