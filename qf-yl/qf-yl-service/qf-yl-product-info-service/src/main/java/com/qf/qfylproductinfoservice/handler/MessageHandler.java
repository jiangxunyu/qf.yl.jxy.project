package com.qf.qfylproductinfoservice.handler;

import com.qf.constant.RabbitmqConstant;
import com.qf.entity.Product;
import com.qf.product.info.api.IProductInfoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @Autowired
    private IProductInfoService productInfoService;

    @RabbitListener(queues = RabbitmqConstant.PRODUCT_ADD_QUEUE)
    public void process(Product product){

        productInfoService.initProductInfo(product);
    }

}
