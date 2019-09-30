package com.qf.qfylemailprovider.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.constant.RabbitmqConstant;
import com.qf.dto.UuidAndUser;
import com.qf.email.api.IEmailService;
import com.qf.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MessageHandler {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private JavaMailSender sender;

    @RabbitListener(queues = RabbitmqConstant.REGISTER_USER_QUEUE)
    public void process(UuidAndUser uuidAndUser){

//        System.out.println(user.getEmail());
//        Long id = user.getId();

        emailService.activeUser(uuidAndUser);
//        MimeMessage message = sender.createMimeMessage();
//
//        MimeMessageHelper mailMessage = null;
//        try {
//            mailMessage = new MimeMessageHelper(message,true);
//            mailMessage.setSubject("用户激活");
//            mailMessage.setText("点击激活用户<a href='http://localhost:8098/active？id="+id+"'>点击激活</a>",true);
//            mailMessage.setFrom("2469193981@qq.com");
//            mailMessage.setTo(user.getEmail());
//            sender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }

}
