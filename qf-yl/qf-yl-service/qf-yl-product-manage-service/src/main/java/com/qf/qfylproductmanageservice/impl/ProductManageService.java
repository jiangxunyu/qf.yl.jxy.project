package com.qf.qfylproductmanageservice.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.constant.RabbitmqConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.mapper.ProductMapper;
import com.qf.product.info.api.IProductInfoService;
import com.qf.product.manage.api.IProductManageService;
import com.qf.yl.searh.api.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Service
public class ProductManageService implements IProductManageService {

    @Autowired
    private ProductMapper mapper;

    @Reference
    private ISearchService searchService;

    @Reference
    private IProductInfoService productInfoService;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Product> selectProducts() {

        List<Product> products = mapper.selectProducts();
        return products;
    }

    @Override
    public void addProduct(Product product) {
        //插入数据库
        mapper.insert(product);

        //主键回填
        Long id = product.getId();

        product.setId(id);
        //往消息队列里发
        rabbitTemplate.convertAndSend(RabbitmqConstant.PRODUCT_ADD_EXCHANGE,
                RabbitmqConstant.PRODUCT_ADD_ROUTING_KEY,
                product);

        //添加数据至solr
//        SolrInputDocument document = new SolrInputDocument();
//        document.addField("id", id);
//        document.addField("product_name", product.getName());
//        document.addField("product_price", product.getPrice().intValue());
//        document.addField("product_image", product.getImage());
//        document.addField("product_sale_point", product.getSalePoint());
//        try {
//            solrClient.add(document);
//            solrClient.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //往消息队列里发
        rabbitTemplate.convertAndSend(RabbitmqConstant.PRODUCT_ADD_EXCHANGE,
                RabbitmqConstant.INIT_PRODUCT_ROUTING_KEY,
                product);
        //生成静态页面
//        productInfoService.initProductInfo(product);
    }

    @Override
    public ResultBean deleteProduct(Long id) {

        int i = mapper.deleteByPrimaryKey(id);
        if (i >= 1) {
            return ResultBean.success("删除商品成功");
        }
        return ResultBean.error("删除商品失败");
    }

    @Override
    public ResultBean selectProductById(Long id) {

        Product product = mapper.selectByPrimaryKey(id);
        if (product != null) {
            return ResultBean.success(product, "存在该用户");
        }
        return ResultBean.error("用户不存在");
    }

    @Override
    public void editorProduct(Product product) {

        mapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public PageInfo<Product> getPageInfo(int pageNue, int pageSize) {
        //让PageHelper通过两个关键参数，来自己生成相应的参数。
        PageHelper.startPage(pageNue, pageSize);

        //调用楼上的selectProducts方法
        List<Product> products = selectProducts();
        PageInfo<Product> pageInfo = new PageInfo<>(products, 3);


        return pageInfo;
    }


}
