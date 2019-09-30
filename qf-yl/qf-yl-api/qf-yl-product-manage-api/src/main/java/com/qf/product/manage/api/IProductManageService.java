package com.qf.product.manage.api;

import com.github.pagehelper.PageInfo;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;

import java.util.List;

public interface IProductManageService {


    List<Product> selectProducts();

    void addProduct(Product product);

    ResultBean deleteProduct(Long id);

    ResultBean selectProductById(Long id);

    void editorProduct(Product product);

    PageInfo<Product> getPageInfo(int pageNue, int pageSize);

}
