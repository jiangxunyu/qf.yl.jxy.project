package com.qf.yl.searh.api;

import com.qf.dto.ResultBean;
import com.qf.entity.Product;

import java.util.List;

public interface ISearchService {

    ResultBean initSolrData();

    ResultBean<List<Product>> searchByKeyword(String keyword);

    void addProductSolr(Product product);
}
