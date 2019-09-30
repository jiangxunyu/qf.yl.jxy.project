package com.qf.qfylproducttypeservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.ProductType;
import com.qf.mapper.ProductMapper;
import com.qf.mapper.ProductTypeMapper;
import com.qf.product.type.api.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class ProductTypeService implements IProductTypeService {

    @Autowired
    private ProductTypeMapper mapper;

    @Override
    public List<ProductType> selectProductTypes() {

        List<ProductType> products = mapper.selectProductTypes();

        return products;
    }
}
