package com.qf.qfylshoppingcartweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.entity.ProductType;
import com.qf.entity.ShoppingCart;
import com.qf.entity.User;
import com.qf.product.type.api.IProductTypeService;
import com.qf.shopping.cart.api.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qf.constant.CookieConstant;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("shopping")
public class ShoppingCartController {

    @Reference
    private IShoppingCartService shoppingService;

    @Reference
    private IProductTypeService productService;

    @RequestMapping("showOrder")
    public String showCart(Model model) {

        List<ProductType> productTypes = productService.selectProductTypes();
        if (productTypes != null) {
            model.addAttribute("productTypes", productTypes);
        }

        List<ShoppingCart> shoppingCarts = shoppingService.selectShoppingCart();

        if (shoppingCarts != null) {
            model.addAttribute("shoppingCarts", shoppingCarts);
        }
        return "Cart";
    }

    @RequestMapping("delete")
    public String deleteShoppingCart(Long id, Model model) {

        List<ProductType> productTypes = productService.selectProductTypes();
        if (productTypes != null) {
            model.addAttribute("productTypes", productTypes);
        }

        shoppingService.deleteById(id);
        List<ShoppingCart> shoppingCarts = shoppingService.selectShoppingCart();

        if (shoppingCarts != null) {
            model.addAttribute("shoppingCarts", shoppingCarts);
        }
        return "Cart";

    }

    /*
    测试未登录状态下添加商品接口
     */
    @RequestMapping("addProduct/{id}/{count}")
    @ResponseBody
    public ResultBean addProduct(@PathVariable Long id, @PathVariable int count,
                                 @CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                 HttpServletResponse response,
                                 HttpServletRequest request) {

        //判断是否已登录
        Object o = request.getAttribute("user");
        if (o != null) {

            //已登录
            User user = (User) o;
            ResultBean resultBean = shoppingService.addToCart(id, count, user.getId().toString());
            return resultBean;
        }

        //访问添加购物车接口，根据cookie判断是否有创建购物车，没有则新建一个cookie
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        //往redis中添加uuid
        ResultBean resultBean = shoppingService.addToCart(id, count, uuid);

        setCookie(uuid, response);
        return resultBean;
    }

    /**
     * 未登录状态下删除商品的接口
     */
    @RequestMapping("delete/{productId}")
    @ResponseBody
    public ResultBean deleteProduct(@PathVariable Long productId,
                                    @CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                    HttpServletResponse response,
                                    HttpServletRequest request) {

        //判断是否已登录
        Object o = request.getAttribute("user");
        if (o != null) {

            //已登录
            User user = (User) o;
            ResultBean resultBean = shoppingService.deleteProduct(productId, user.getId().toString());
            return resultBean;
        }

        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        ResultBean resultBean = shoppingService.deleteProduct(productId, uuid);

        setCookie(uuid, response);
        return resultBean;
    }

    /**
     * 测试未登录状态下更新购物车接口
     *
     * @param uuid
     * @param response
     * @return
     */
    @RequestMapping("updateCart/{productId}/{count}")
    @ResponseBody
    public ResultBean updateCart(@PathVariable Long productId, @PathVariable int count,
                                 @CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                 HttpServletResponse response,
                                 HttpServletRequest request) {

        //判断是否已登录
        Object o = request.getAttribute("user");
        if (o != null) {

            //已登录
            User user = (User) o;
            ResultBean resultBean = shoppingService.updataCart(productId, count, user.getId().toString());
            return resultBean;
        }

        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        ResultBean resultBean = shoppingService.updataCart(productId, count, uuid);

        setCookie(uuid, response);
        return resultBean;

    }

    /*
      测试未登录状态获取购物车接口
     */
    @RequestMapping("getCart")
    @ResponseBody
    public ResultBean showVisitorCart(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                      HttpServletResponse response,
                                      HttpServletRequest request) {

        //判断是否已登录
        Object o = request.getAttribute("user");
        if (o != null) {

            //已登录
            User user = (User) o;
            ResultBean resultBean = shoppingService.showVisitorCart(user.getId().toString());
            return resultBean;
        }

        //判断cookie是否为空
        if (uuid == null || "".equals(uuid)) {

            return ResultBean.error("当前用户没有购物车");
        }

        ResultBean resultBean = shoppingService.showVisitorCart(uuid);

        setCookie(uuid, response);
        return resultBean;
    }

    public void setCookie(String uuid, HttpServletResponse response) {

        //cookie不为空，更新cookie时间
        Cookie cookie = new Cookie(CookieConstant.USER_CART, uuid);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        //二级域名携带cookie
//        cookie.setDomain("qf.com");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

}
