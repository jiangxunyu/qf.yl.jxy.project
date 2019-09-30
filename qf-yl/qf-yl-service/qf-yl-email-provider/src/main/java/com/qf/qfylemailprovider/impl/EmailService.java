package com.qf.qfylemailprovider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dto.UuidAndUser;
import com.qf.email.api.IEmailService;
import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Service
public class EmailService implements IEmailService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender sender;

    @Override
    public void activeUser(UuidAndUser uuidAndUser) {

//        Long id = user.getId();

        String uuid = uuidAndUser.getUuid();

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper mailMessage = null;
        try {
            mailMessage = new MimeMessageHelper(message,true);
            mailMessage.setSubject("用户激活");
            mailMessage.setText("点击激活用户<a href='http://localhost:8098/active?uuid="+uuid+"'>点击激活</a>",true);
            mailMessage.setFrom("2469193981@qq.com");
            mailMessage.setTo(uuidAndUser.getUser().getEmail());
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
