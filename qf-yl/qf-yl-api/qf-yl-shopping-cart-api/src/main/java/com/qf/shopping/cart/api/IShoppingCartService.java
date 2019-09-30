package com.qf.shopping.cart.api;

import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.entity.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {
    ResultBean addProduct(Product product);

    List<ShoppingCart> selectShoppingCart();

    void deleteById(Long id);

    ResultBean showVisitorCart(String uuid);

    ResultBean addToCart(Long id, int count, String uuid);

    ResultBean updataCart(Long productId, int count, String uuid);

    ResultBean deleteProduct(Long productId, String uuid);
}
