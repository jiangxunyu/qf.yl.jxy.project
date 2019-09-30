package com.qf.email.api;

import com.qf.dto.UuidAndUser;
import com.qf.entity.User;

public interface IEmailService {

    void activeUser(UuidAndUser uuidAndUser);
}
