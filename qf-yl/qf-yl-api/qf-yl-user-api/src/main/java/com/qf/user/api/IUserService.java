package com.qf.user.api;

import com.qf.base.IBaseService;
import com.qf.dto.ResultBean;
import com.qf.entity.User;

public interface IUserService extends IBaseService<User> {

    User checkLogin(User user);

    ResultBean checkIsLogin(String uuid);

    void logout(String uuid);

    ResultBean activeUser(String uuid);
}
