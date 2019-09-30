package com.qf.qfylsearchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.entity.ProductType;
import com.qf.product.type.api.IProductTypeService;
import com.qf.yl.searh.api.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @Reference
    private IProductTypeService productService;

    @RequestMapping("initSolrData")
    @ResponseBody
    public ResultBean initSolrData(){

        ResultBean resultBean = searchService.initSolrData();
        return resultBean;
    }

    @RequestMapping("products")
    public String searchByKeyword(String keyword, Model model){

        List<ProductType> productTypes = productService.selectProductTypes();
        if(productTypes!=null){
            model.addAttribute("productTypes",productTypes);
        }

        ResultBean<List<Product>> resultBean = searchService.searchByKeyword(keyword);
        if(resultBean!=null){
            model.addAttribute("products",resultBean.getData());
        }
        return "SearchList";
    }

    @RequestMapping
    public String show(Model model){
        List<ProductType> productTypes = productService.selectProductTypes();
        if(productTypes!=null){
            model.addAttribute("productTypes",productTypes);
        }
        return "SearchList";
    }

}
