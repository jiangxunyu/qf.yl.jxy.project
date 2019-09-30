package com.qf.qfylindexweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Product;
import com.qf.entity.ProductType;
import com.qf.product.info.api.IProductInfoService;
import com.qf.product.type.api.IProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Reference
    private IProductTypeService productService;

    @Reference
    private IProductInfoService productInfoService;

    @RequestMapping("index")
    public String showIndex(Model model){

        List<ProductType> productTypes = productService.selectProductTypes();
        if(productTypes!=null){
            model.addAttribute("productTypes",productTypes);
        }
        return "index";
    }

    @RequestMapping("productList")
    public String productShow(String typeName,Model model){

        List<ProductType> productTypes = productService.selectProductTypes();
        if(productTypes!=null){
            model.addAttribute("productTypes",productTypes);
        }

        List<Product> products = productInfoService.selectProductByType(typeName);
        if(products!=null){
            model.addAttribute("products",products);
        }

        return "productList";
    }
}
