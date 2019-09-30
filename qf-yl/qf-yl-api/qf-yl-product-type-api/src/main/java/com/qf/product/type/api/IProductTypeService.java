package com.qf.product.type.api;

import com.qf.entity.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> selectProductTypes();
}
