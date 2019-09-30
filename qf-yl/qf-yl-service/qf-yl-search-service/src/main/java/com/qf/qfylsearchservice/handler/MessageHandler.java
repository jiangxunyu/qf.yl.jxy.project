package com.qf.qfylsearchservice.handler;

import com.qf.entity.Product;
import com.qf.yl.searh.api.ISearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qf.constant.RabbitmqConstant;

@Component
public class MessageHandler {

    @Autowired
    private ISearchService searchService;

    @RabbitListener(queues = RabbitmqConstant.PRODUCT_ADD_QUEUE)
    public void process(Product product){

        searchService.addProductSolr(product);
    }

}
