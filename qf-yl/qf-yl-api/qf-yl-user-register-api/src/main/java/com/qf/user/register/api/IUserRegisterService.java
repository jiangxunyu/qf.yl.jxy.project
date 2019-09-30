package com.qf.user.register.api;

import com.qf.dto.ResultBean;
import com.qf.dto.UserAndCode;
import com.qf.entity.User;

public interface IUserRegisterService {

    ResultBean checkIsRegister(User user);

    ResultBean checkRegisterCode(UserAndCode userAndCode);
}
