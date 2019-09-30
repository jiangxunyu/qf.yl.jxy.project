package com.qf.qfylproductmanageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.product.info.api.IProductInfoService;
import com.qf.product.manage.api.IProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductManageService productService;

    @Autowired
    private FastFileStorageClient client;

    @Reference
    private IProductInfoService productInfoService;

    @Value("${image.server}")
    private String imageServer;

    @RequestMapping("show")
    public String showProducts(Model model) {

        List<Product> productList = productService.selectProducts();
        if (productList != null) {
            model.addAttribute("products", productList);
        }
        return "products";
    }

    /**
     * 分布显示商品
     */
    @RequestMapping("page/{pageNum}")
    public String getProductByPage(@PathVariable int pageNum,Model model){

        PageInfo<Product> pageInfo = null;
        if(pageNum==0){
            pageInfo = productService.getPageInfo(1,6);
        }else{
            pageInfo = productService.getPageInfo(pageNum,6);
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("products",pageInfo.getList());
        return "products";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(Product product) {

        if(product.getTypeId()==3){
            product.setTypeName("耐火电缆");
        }else if(product.getTypeId()==4){
            product.setTypeName("电话电缆");
        }else{
            product.setTypeName("网络电缆");
        }
        productService.addProduct(product);
        return "redirect:/product/page/1";
    }

    /**
     * 删除商品
     * 路径传参
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    public ResultBean deleteProduct(@PathVariable Long id){

        ResultBean resultBean = productService.deleteProduct(id);
        return resultBean;
    }

    /**
     * 查询商品
     */
    @RequestMapping("select/{id}")
    @ResponseBody
    public ResultBean selectProduct(@PathVariable Long id){

        ResultBean resultBean = productService.selectProductById(id);
        return resultBean;
    }

    /**
     * 修改商品
     */
    @RequestMapping(value = "editor",method = RequestMethod.POST)
    public String editorProduct(Product product){

        if(product.getTypeId()==3){
            product.setTypeName("耐火电缆");
        }else if(product.getTypeId()==4){
            product.setTypeName("电话电缆");
        }else{
            product.setTypeName("网络电缆");
        }
        productService.editorProduct(product);
        return "redirect:/product/page/1";
    }

    @RequestMapping("batchGenerator")
    @ResponseBody
    public ResultBean batchGenerator(){

        ResultBean resultBean = productInfoService.batchGeneratorProductInfo();
        return resultBean;
    }

}
