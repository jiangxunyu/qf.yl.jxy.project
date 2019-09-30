package com.qf.qfylproductinfoservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.mapper.ProductMapper;
import com.qf.product.info.api.IProductInfoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Service
public class ProductInfoService implements IProductInfoService {

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private Configuration configuration;

    @Value("${itemServices.path}")
    private String itemServicePath;

    @Override
    public List<Product> getProductInfoById(Long id) {

        List<Product> products = new ArrayList<>();
        Product product = mapper.selectByPrimaryKey(id);
        products.add(product);
        return products;
    }

    @Override
    public List<Product> selectProductByType(String typeName) {
        List<Product> products = mapper.selectProductsByType(typeName);
        return products;
    }

    /**
     * 生成单个静态页面
     */
    public ResultBean initProductInfo(Product product) {

        try {
            Template template = configuration.getTemplate("SD_info.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("product", product);

            Writer out = new FileWriter(itemServicePath + product.getId() + ".html");

            template.process(data, out);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("生成静态页面失败");
        }
        return ResultBean.success("生成静态页面成功");
    }

    /**
     * 批量生成静态页面
     */
    public ResultBean batchGeneratorProductInfo() {

        //查出所有商品
        List<Product> products = mapper.selectProducts();

        //当前机器的核心数
        int corePoolSize = Runtime.getRuntime().availableProcessors();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize * 2, 20L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000));

        for (Product product : products) {

            executor.submit(new GenerateProductInfo(product));

        }
        return ResultBean.success("批量生成页面成功");
    }

    /**
     * 内部类
     */
    class GenerateProductInfo implements Callable<Boolean> {

        private Product product;

        //构造方法
        public GenerateProductInfo(Product product) {
            this.product = product;
        }

        @Override
        public Boolean call() throws Exception {

            try {
                Template template = configuration.getTemplate("SD_info.ftl");
                Map<String, Object> data = new HashMap<>();
                data.put("product", this.product);

                Writer out = new FileWriter(itemServicePath + product.getId() + ".html");

                template.process(data, out);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

}
