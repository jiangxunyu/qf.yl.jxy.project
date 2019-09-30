package com.qf.mapper;

import com.qf.entity.Product;
import com.qf.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);

    List<ProductType> selectProductTypes();
}