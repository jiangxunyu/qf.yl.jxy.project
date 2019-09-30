package com.qf.qfylproductinfoweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.entity.ProductType;
import com.qf.product.info.api.IProductInfoService;
import com.qf.product.type.api.IProductTypeService;
import com.qf.shopping.cart.api.IShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductInfoController {

    @Reference
    private IProductInfoService productService;

    @Reference
    private IProductTypeService productTypeService;

    @Reference
    private IShoppingCartService shoppingService;

    @RequestMapping("productInfo")
    public String getProductInfoById(Long id, Model model){

        List<ProductType> productTypes = productTypeService.selectProductTypes();
        if(productTypes!=null){
            model.addAttribute("productTypes",productTypes);
        }
        List<Product> products = productService.getProductInfoById(id);
        if(products!=null){
            model.addAttribute("products",products);
        }
        return "SD_info";
    }

    @RequestMapping("addProduct")
    public String addProduct(Long id){

        List<Product> products = productService.getProductInfoById(id);

        ResultBean resultBean = shoppingService.addProduct(products.get(0));

        if (resultBean.getErrno()==0){
            return "redirect:http://localhost:8097/shopping/showOrder";
        }
        return "SD_info";
    }

}
