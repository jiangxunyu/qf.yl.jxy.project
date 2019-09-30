package com.qf.product.info.api;

import com.qf.dto.ResultBean;
import com.qf.entity.Product;

import java.util.List;

public interface IProductInfoService {
    List<Product> getProductInfoById(Long id);

    List<Product> selectProductByType(String typeName);

    ResultBean initProductInfo(Product product);

    ResultBean batchGeneratorProductInfo();
}
