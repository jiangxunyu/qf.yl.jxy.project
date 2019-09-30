package com.qf.sms.api;

import com.qf.dto.ResultBean;

public interface ISmsService {

    ResultBean sendSms(String mobile, String tplId, String code);

}
