package com.qf.mapper;

import com.qf.entity.Product;

import java.util.List;

public interface ProductMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectProducts();

    List<Product> selectProductsByType(String typeName);

}